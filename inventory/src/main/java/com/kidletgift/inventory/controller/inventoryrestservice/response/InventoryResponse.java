package com.kidletgift.inventory.controller.inventoryrestservice.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class InventoryResponse {

    private String status;
    private String errorCode;
    private String errorDescription;
    private GiftItem giftItem;
    private List<GiftItem> giftItems;

}
