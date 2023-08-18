package com.kidletgift.order.service.cart.serviceimpl.servicesupport;

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
