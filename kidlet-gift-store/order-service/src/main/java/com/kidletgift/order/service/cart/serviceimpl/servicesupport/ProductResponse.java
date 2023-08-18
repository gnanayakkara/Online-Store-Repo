package com.kidletgift.order.service.cart.serviceimpl.servicesupport;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String status;
    private String errorCode;
    private String errorDescription;
    private GiftItem giftItem;
    private List<GiftItem> giftItems;

}
