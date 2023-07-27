package com.kidletgift.order.controller.cartrestservice;

import com.kidletgift.order.controller.cartrestservice.request.AddToCartRequest;
import com.kidletgift.order.dto.AddToCartDTO;
import com.kidletgift.order.dto.CartItemDTO;
import com.kidletgift.order.mapper.cart.CartMapper;
import com.kidletgift.order.service.cart.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CartItemDTO> addItemToCart(@RequestBody AddToCartRequest addToCartRequest) throws Exception {

        AddToCartDTO addToCartDTO = cartMapper.addToCartRequestToAddToCartDTO(addToCartRequest);
        CartItemDTO cartItemDTO = cartService.addItemToCart(addToCartDTO);

        ResponseEntity<CartItemDTO> responseEntity = new ResponseEntity<>(cartItemDTO, HttpStatus.OK);
        return responseEntity;
    }

}
