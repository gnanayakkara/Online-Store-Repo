package com.kidletgift.order.controller.cartrestservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceChangedDetails {

    String changedTo;
    Double beforePrice;
    Double afterPrice;
}
