package com.kidletgift.order.model.orderdoc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchasedItems {

    private String itemId;
    private Integer itemQuantity;
    private Double itemPrice;
    private Discount discount;
    private Double sumAmount;
}
