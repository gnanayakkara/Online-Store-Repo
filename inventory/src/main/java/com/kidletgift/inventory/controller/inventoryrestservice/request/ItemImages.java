package com.kidletgift.inventory.controller.inventoryrestservice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemImages {

    private Integer imageOrder;
    private String imageUrl;

}
