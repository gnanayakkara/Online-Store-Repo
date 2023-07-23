package com.kidletgift.order.service.cart.serviceimpl;

import com.kidletgift.order.dto.AddToCartDTO;
import com.kidletgift.order.repository.OrderRepository;
import com.kidletgift.order.service.cart.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;

    @Autowired
    CartServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addItemToCart(AddToCartDTO addToCartDTO) {


    }
}
