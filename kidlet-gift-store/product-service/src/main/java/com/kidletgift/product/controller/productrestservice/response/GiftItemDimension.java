package com.kidletgift.product.controller.productrestservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftItemDimension {

    private Double length;
    private Double width;
    private Double height;
    private Double weight;

    private String sizeMetric;
    private String weightMetric;
}
