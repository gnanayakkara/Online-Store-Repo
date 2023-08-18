package com.kidletgift.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceChangedDetailsDTO {

    String changedTo;
    Double beforePrice;
    Double afterPrice;
}
