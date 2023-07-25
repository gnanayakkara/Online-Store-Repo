package com.kidletgift.order.service.cart.serviceimpl;

import com.kidletgift.order.dto.AddToCartDTO;
import com.kidletgift.order.dto.CartItemDTO;
import com.kidletgift.order.mapper.cart.CartMapper;
import com.kidletgift.order.model.orderdoc.CartItem;
import com.kidletgift.order.model.orderdoc.OrderDoc;
import com.kidletgift.order.repository.OrderRepository;
import com.kidletgift.order.repository.repositoryinterface.CustomOrderRepository;
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

    private final CustomOrderRepository customOrderRepository;

    private final CartMapper cartMapper;

    @Autowired
    CartServiceImpl(OrderRepository orderRepository, CustomOrderRepository customOrderRepository, CartMapper cartMapper) {
        this.orderRepository = orderRepository;
        this.customOrderRepository = customOrderRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartItemDTO addItemToCart(AddToCartDTO addToCartDTO) throws Exception {

        if (addToCartDTO.getUserId().isBlank()) {//TODO User not logged in, Add to session

        } else {

            Optional<OrderDoc> byUserIdDoc = orderRepository.findByUserId(addToCartDTO.getUserId());

            if (byUserIdDoc.isPresent()) { //Already available

                if (byUserIdDoc.get().getCartItems().isEmpty()) { //check if cart is empty

                    CartItem cartItem = cartMapper.cartItemDTOToCartItem(addToCartDTO.getCartItemDTO());
                    customOrderRepository.addItemToCart(addToCartDTO.getUserId(),cartItem);

                } else { // if cart is not empty

                    //check if same item available, if true update item count

                    List<Integer> updatedItem = byUserIdDoc.get().getCartItems().stream()
                            .filter(cartItem -> cartItem.getItemId().equals(addToCartDTO.getCartItemDTO().getItemId()))
                            .map(cartItem -> cartItem.getItemQuantity() + 1)
                            .collect(Collectors.toList());

                    customOrderRepository.addItemToCart(addToCartDTO.getUserId(),
                            cartMapper.cartItemDTOToCartItem(addToCartDTO.getCartItemDTO()));
                }
            } else {
                //Fresh Customer or No previous orders

                List<CartItem> cartItems = new ArrayList<>();
                cartItems.add(cartMapper.cartItemDTOToCartItem(addToCartDTO.getCartItemDTO()));

                OrderDoc saveOrderDoc = new OrderDoc();
                saveOrderDoc.setUserId(addToCartDTO.getUserId());
                saveOrderDoc.setCartItems(cartItems);

                orderRepository.save(saveOrderDoc);

                return addToCartDTO.getCartItemDTO();
            }
        }

        return null;
    }
}
