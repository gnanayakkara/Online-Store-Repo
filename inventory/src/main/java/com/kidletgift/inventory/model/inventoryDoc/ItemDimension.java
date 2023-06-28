package com.kidletgift.inventory.model.inventoryDoc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDimension {

    private Double length;
    private Double width;
    private Double height;
    private Double weight;

    private String sizeMetric;
    private String weightMetric;
}
