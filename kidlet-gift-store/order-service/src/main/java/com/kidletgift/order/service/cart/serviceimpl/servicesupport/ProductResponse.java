package com.kidletgift.order.service.cart.serviceimpl.servicesupport;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
