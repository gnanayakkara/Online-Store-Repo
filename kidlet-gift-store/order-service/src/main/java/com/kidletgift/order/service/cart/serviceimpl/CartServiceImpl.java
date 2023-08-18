package com.kidletgift.order.service.cart.serviceimpl;

import com.kidletgift.order.constants.CartConstant;
import com.kidletgift.order.dto.CartDTO;
import com.kidletgift.order.dto.CartItemDTO;
import com.kidletgift.order.dto.PriceChangedDetailsDTO;
import com.kidletgift.order.mapper.cart.CartMapper;
import com.kidletgift.order.model.order.CartItem;
import com.kidletgift.order.model.order.OrderDoc;
import com.kidletgift.order.repository.CartRepository;
import com.kidletgift.order.repository.OrderRepository;
import com.kidletgift.order.repository.repositoryinterface.CustomCartRepository;
import com.kidletgift.order.service.cart.serviceimpl.servicesupport.GiftItem;
import com.kidletgift.order.service.cart.serviceimpl.servicesupport.ProductResponse;
import com.kidletgift.order.service.cart.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomCartRepository customCartRepository;
    private final CartMapper cartMapper;
    private final WebClient webClient;


    @Autowired
    CartServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, CustomCartRepository customCartRepository,
                    CartMapper cartMapper, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.customCartRepository = customCartRepository;
        this.cartMapper = cartMapper;
        this.webClient = webClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CartItemDTO addItemToCart(CartDTO cartDTO) throws Exception {

        CartItemDTO cartItemDTO = cartDTO.getCartItemDTO();

        if (cartDTO.getUserId().isBlank()) {
            //TODO User not logged in, Add to session
        } else {

            Optional<OrderDoc> byUserIdDoc = orderRepository.findByUserId(cartDTO.getUserId());

            if (byUserIdDoc.isPresent()) { //Already available

                if (byUserIdDoc.get().getCartItems().isEmpty()) { //check if cart is empty

                    addItemToCart(cartItemDTO, cartDTO.getUserId());

                } else { // if cart is not empty

                    //check if same item available, if true update item count
                    List<CartItem> updatedItem = byUserIdDoc.get().getCartItems().stream()
                            .filter(cartItem -> cartItem.getItemId().equals(cartItemDTO.getItemId()))
                            .collect(Collectors.toList());

                    if (!updatedItem.isEmpty()) { //update the item

                        CartItem cartItem = updatedItem.get(0);

                        boolean isUpdated = customCartRepository
                                .updateCartItemQty(cartDTO.getUserId(), cartItem, cartItemDTO.getItemQuantity());

                        if (isUpdated) {
                            cartItemDTO.setItemQuantity(cartItem.getItemQuantity() + cartItemDTO.getItemQuantity());
                        }

                    } else { //No item found. Add to cart

                        addItemToCart(cartItemDTO, cartDTO.getUserId());
                    }
                }

            } else { //Fresh Customer or No previous orders

                List<CartItem> cartItems = new ArrayList<>();
                cartItems.add(cartMapper.cartItemDTOToCartItem(cartDTO.getCartItemDTO()));

                OrderDoc saveOrderDoc = new OrderDoc();
                saveOrderDoc.setUserId(cartDTO.getUserId());
                saveOrderDoc.setCartItems(cartItems);

                orderRepository.save(saveOrderDoc);

            }
        }
        return cartItemDTO;
    }

    /**
     * Add or Update Item in cart
     *
     * @param cartItemDTO
     * @param userId
     * @throws Exception
     */
    private void addItemToCart(CartItemDTO cartItemDTO, String userId) throws Exception {

        CartItem cartItem = cartMapper.cartItemDTOToCartItem(cartItemDTO);
        customCartRepository.addItemToCart(userId, cartItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean removeItemFromCart(CartDTO cartDTO) throws Exception {

        return customCartRepository.removeItemFromCart(cartDTO.getUserId(), cartDTO.getCartItemDTO().getItemId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CartItemDTO> getCartItems(String userId) throws Exception {

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(userId).getCartItems();

        //If cart is not empty
        if (!cartItems.isEmpty()) {

            Map<String,CartItemDTO> cartItemDTOS = cartItems.stream()
                    .map(cartMapper::cartItemToCartItemDTO)
                    .collect(Collectors.toMap(CartItemDTO::getItemId, Function.identity()));

            List<String> itemIds =  cartItems.stream().map(CartItem::getItemId).toList();

            List<GiftItem> giftItems = getGiftItemsListFromProductService(itemIds);

            if (cartItemDTOS.size() != giftItems.size()){
                throw new Exception("All items did not receive from Product service");
            }

            for (GiftItem giftItem : giftItems) {

                CartItemDTO cartItemDTO = cartItemDTOS.get(giftItem.getItemId());

                //Update the cart item if price has been changed
                if (giftItem.getItemPrice() != cartItemDTO.getItemPrice()){

                    CartItem cartItem = CartItem.builder()
                            .itemId(giftItem.getItemId())
                            .itemPrice(giftItem.getItemPrice())
                            .build();

                    Boolean isItemUpdated = customCartRepository.updateCartItemPrice(userId,cartItem);

                    Double beforePrice = cartItemDTO.getItemPrice();
                    Double afterPrice = giftItem.getItemPrice();

                    PriceChangedDetailsDTO priceChangedDetailDTO = PriceChangedDetailsDTO.builder()
                            .changedTo(afterPrice > beforePrice ? CartConstant.INCREASED : CartConstant.DECREASED)
                            .beforePrice(beforePrice)
                            .afterPrice(giftItem.getItemPrice())
                            .build();

                    //Update new price and price changed details
                    cartItemDTO.setPriceChangedDetails(priceChangedDetailDTO);
                    cartItemDTO.setItemPrice(afterPrice);
                }

            }

            return cartItemDTOS.values().stream().toList();
        }

        return null;
    }

    private List<GiftItem> getGiftItemsListFromProductService(List<String> itemIds) throws Exception {

        ProductResponse productResponse = webClient.get()
                .uri("http://localhost:8090/product",
                        uriBuilder -> uriBuilder.queryParam("itemIds", itemIds).build())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        if (productResponse != null && productResponse.getStatus().equals("00")) {
            return  productResponse.getGiftItems();
        } else {
            throw new Exception("Unable to retrieve Gift Item details from Product Service");
        }
    }
}
