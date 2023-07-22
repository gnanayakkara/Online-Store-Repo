package com.kidletgift.order.controller.orderrestservice;

import com.kidletgift.order.mapper.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderMapper orderMapper;

    @Autowired
    OrderController(OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }



}
