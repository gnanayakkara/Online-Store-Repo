package com.kidletgift.product.controller.productrestservice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDescriptionWithImages {

    private Integer imageOrder;
    private String imageDescription;
    private String imageUrl;
}
