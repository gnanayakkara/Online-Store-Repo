package com.kidletgift.order.model.order;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PurchaseHistory {

    private Date purchasedDate;
    private Address address;
    private Double totalAmount;
    private List<PurchasedItems> purchasedItems;
    private Boolean isAGiftItem;
    private GiftDetails giftDetails;
}
