package com.kidletgift.inventory.controller.inventoryrestservice.response;

import lombok.*;

@Getter
@Setter
@Builder
public class InventoryResponse {

    private String status;
    private String errorCode;
    private String errorDescription;

}
