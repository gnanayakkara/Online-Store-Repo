package com.kidletgift.inventory.controller.inventoryrestservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftItemDescriptionWithImages {

    private Integer imageOrder;
    private String imageDescription;
    private String imageUrl;
}
