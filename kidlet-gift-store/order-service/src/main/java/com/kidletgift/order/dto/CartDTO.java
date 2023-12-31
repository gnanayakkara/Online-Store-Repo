package com.kidletgift.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDTO {

    private String userId;
    private CartItemDTO cartItemDTO;
}
