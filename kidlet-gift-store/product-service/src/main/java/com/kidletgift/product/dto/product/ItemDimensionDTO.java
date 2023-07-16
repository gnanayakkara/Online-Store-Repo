package com.kidletgift.product.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDimensionDTO {

    private Double length;
    private Double width;
    private Double height;
    private Double weight;

    private String sizeMetric;
    private String weightMetric;
}
