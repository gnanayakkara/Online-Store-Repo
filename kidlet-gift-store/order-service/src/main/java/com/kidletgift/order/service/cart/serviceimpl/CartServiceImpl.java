package com.kidletgift.order.service.cart.serviceimpl;

import com.kidletgift.order.dto.AddToCartDTO;
import com.kidletgift.order.dto.CartItemDTO;
import com.kidletgift.order.mapper.cart.CartMapper;
import com.kidletgift.order.model.orderdoc.CartItem;
import com.kidletgift.order.model.orderdoc.OrderDoc;
import com.kidletgift.order.repository.OrderRepository;
import com.kidletgift.order.repository.repositoryinterface.CustomCartRepository;
import com.kidletgift.order.service.cart.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;

    private final CustomCartRepository customCartRepository;

    private final CartMapper cartMapper;

    @Autowired
    CartServiceImpl(OrderRepository orderRepository, CustomCartRepository customCartRepository, CartMapper cartMapper) {
        this.orderRepository = orderRepository;
        this.customCartRepository = customCartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartItemDTO addItemToCart(AddToCartDTO addToCartDTO) throws Exception {

        CartItemDTO cartItemDTO = addToCartDTO.getCartItemDTO();

        if (addToCartDTO.getUserId().isBlank()) {
            //TODO User not logged in, Add to session
        } else {

            Optional<OrderDoc> byUserIdDoc = orderRepository.findByUserId(addToCartDTO.getUserId());

            if (byUserIdDoc.isPresent()) { //Already available

                if (byUserIdDoc.get().getCartItems().isEmpty()) { //check if cart is empty

                    addItemToCart (cartItemDTO,addToCartDTO.getUserId());

                } else { // if cart is not empty

                    //check if same item available, if true update item count
                    List<CartItem> updatedItem = byUserIdDoc.get().getCartItems().stream()
                            .filter(cartItem -> cartItem.getItemId().equals(cartItemDTO.getItemId()))
                            .collect(Collectors.toList());

                    if (!updatedItem.isEmpty()) { //update the item

                        CartItem cartItem = updatedItem.get(0);

                        boolean isUpdated = customCartRepository
                                .updateCartItem(addToCartDTO.getUserId(), cartItem, cartItemDTO.getItemQuantity());

                        if (isUpdated) {
                            cartItemDTO.setItemQuantity(cartItem.getItemQuantity() + cartItemDTO.getItemQuantity());
                        }

                    } else { //No item found. Add to cart

                        addItemToCart (cartItemDTO,addToCartDTO.getUserId());
                    }
                }

            } else { //Fresh Customer or No previous orders

                List<CartItem> cartItems = new ArrayList<>();
                cartItems.add(cartMapper.cartItemDTOToCartItem(addToCartDTO.getCartItemDTO()));

                OrderDoc saveOrderDoc = new OrderDoc();
                saveOrderDoc.setUserId(addToCartDTO.getUserId());
                saveOrderDoc.setCartItems(cartItems);

                orderRepository.save(saveOrderDoc);

            }
        }
        return cartItemDTO;
    }

    /**
     * Add or Update Item in cart
     * @param cartItemDTO
     * @param userId
     * @throws Exception
     */
    private void addItemToCart(CartItemDTO cartItemDTO,String userId) throws Exception {

        CartItem cartItem = cartMapper.cartItemDTOToCartItem(cartItemDTO);
        customCartRepository.addItemToCart(userId,cartItem);
    }
}
