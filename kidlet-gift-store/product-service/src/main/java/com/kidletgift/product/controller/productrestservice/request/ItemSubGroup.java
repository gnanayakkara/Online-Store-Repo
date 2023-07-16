package com.kidletgift.product.controller.productrestservice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemSubGroup {

    private String itemId;
    private String itemName;
    private String itemImgUrl;
}
