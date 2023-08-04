package com.kidletgift.order.controller.cartrestservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartGiftItems {

    private String itemId;
    private Integer itemQuantity;
    private Double itemPrice;
}
