package com.kidletgift.product.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ItemOffersDTO {

    private String offerName;
    private String offerCode;
    private Double offerPercentage;
    private Date offerStartDate;
    private Date offerEndDate;
    private Date offerCreatedDate;
    private Date offerModifiedDate;
    private String offerDetails;
    private String offerRemarks;
    private String offerStatus;
}
