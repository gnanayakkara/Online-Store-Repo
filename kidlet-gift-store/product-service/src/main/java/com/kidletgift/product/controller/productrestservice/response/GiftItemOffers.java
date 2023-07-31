package com.kidletgift.product.controller.productrestservice.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class GiftItemOffers {

    private String offerName;
    private String offerCode;
    private Double offerPercentage;
    private Date offerStartDate;
    private Date offerEndDate;
}
