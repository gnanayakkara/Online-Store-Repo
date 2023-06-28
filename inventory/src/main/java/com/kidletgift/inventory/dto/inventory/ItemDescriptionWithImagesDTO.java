package com.kidletgift.inventory.dto.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDescriptionWithImagesDTO {

    private Integer imageOrder;
    private String imageDescription;
    private String imageUrl;
}
