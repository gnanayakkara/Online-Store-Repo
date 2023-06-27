package com.kidletgift.inventory.controller.inventory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryResponse {

    public String status;
    public String errorCode;
    public String errorDescription;

}
