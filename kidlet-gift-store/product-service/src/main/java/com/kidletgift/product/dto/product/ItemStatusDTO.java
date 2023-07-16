package com.kidletgift.product.dto.product;

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
