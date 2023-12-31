package com.kidletgift.order.model.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Discount {

    private String discountName;
    private String discountCode;
    private Double discountPercentage;
}
