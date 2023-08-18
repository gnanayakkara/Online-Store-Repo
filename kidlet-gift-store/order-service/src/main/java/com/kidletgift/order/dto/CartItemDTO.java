package com.kidletgift.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDTO {

    private String itemId;
    private Integer itemQuantity;
    private Double itemPrice;

    private PriceChangedDetailsDTO priceChangedDetails;
}
