package com.kidletgift.inventory.dto.inventory;

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
