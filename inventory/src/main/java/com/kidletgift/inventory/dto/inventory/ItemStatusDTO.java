package com.kidletgift.inventory.dto.inventory;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemStatusDTO {

    private String status;
    private Date statusUpdatedDate;
    private String statusUpdatedBy;

}
