package com.kidletgift.order.controller.cartrestservice.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddToCartRequest {

    private String userId;
    @NotNull(message = "Item Id is mandatory")
    private String itemId;
    @NotNull(message = "Item quantity is mandatory")
    private Integer itemQuantity;
    @NotNull(message = "Item price is mandatory")
    private Double itemPrice;
}
