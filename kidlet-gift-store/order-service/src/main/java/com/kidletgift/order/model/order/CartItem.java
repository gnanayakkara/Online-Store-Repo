package com.kidletgift.order.model.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {

    private String itemId;
    private Integer itemQuantity;
    private Double itemPrice;

}
