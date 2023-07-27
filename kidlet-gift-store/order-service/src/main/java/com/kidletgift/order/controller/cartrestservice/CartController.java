package com.kidletgift.order.controller.cartrestservice;

import com.kidletgift.order.controller.cartrestservice.request.CartRequest;
import com.kidletgift.order.dto.CartDTO;
import com.kidletgift.order.dto.CartItemDTO;
import com.kidletgift.order.mapper.cart.CartMapper;
import com.kidletgift.order.service.cart.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    @Autowired
    CartController(CartMapper cartMapper, CartService cartService) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @PostMapping("/addItem")
    public ResponseEntity<CartItemDTO> addItemToCart(@RequestBody CartRequest cartRequest) throws Exception {

        CartDTO cartDTO = cartMapper.cartRequestToCartDTO(cartRequest);
        CartItemDTO cartItemDTO = cartService.addItemToCart(cartDTO);

        ResponseEntity<CartItemDTO> responseEntity = new ResponseEntity<>(cartItemDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("/removeItem")
    public ResponseEntity<Boolean> removeItemFromCart(@RequestBody CartRequest cartRequest) throws Exception {

        CartDTO cartDTO = cartMapper.cartRequestToCartDTO(cartRequest);
        Boolean isRemoved = cartService.removeItemFromCart(cartDTO);

        if (isRemoved) {
            return new ResponseEntity<>(isRemoved,HttpStatus.OK);
        }

        return new ResponseEntity<>(isRemoved,HttpStatus.CONFLICT);
    }

}
