package com.kidletgift.order.controller.cartrestservice;

import com.kidletgift.order.controller.cartrestservice.request.AddToCartRequest;
import com.kidletgift.order.dto.AddToCartDTO;
import com.kidletgift.order.mapper.cart.CartMapper;
import com.kidletgift.order.service.cart.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    @Autowired
    CartController(CartMapper cartMapper,CartService cartService) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @PostMapping("/addItem")
    public void addItemToCart(@RequestBody AddToCartRequest addToCartRequest) {

        AddToCartDTO addToCartDTO = cartMapper.addToCartRequestToAddToCartDTO(addToCartRequest);


    }

}
