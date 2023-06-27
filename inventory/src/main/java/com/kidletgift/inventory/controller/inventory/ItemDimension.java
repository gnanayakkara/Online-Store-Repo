package com.kidletgift.inventory.controller.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDimension {

    private Double height;
    private Double weight;
    private Double size;

    private String heightMetric;
    private String weightMetric;
    private String sizeMetric;
}
