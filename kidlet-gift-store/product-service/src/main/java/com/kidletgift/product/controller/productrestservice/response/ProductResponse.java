package com.kidletgift.product.controller.productrestservice.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponse {

    private String status;
    private String errorCode;
    private String errorDescription;
    private GiftItem giftItem;
    private List<GiftItem> giftItems;

}
