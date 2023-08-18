package com.kidletgift.order.service.cart.serviceimpl.servicesupport;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GiftItemStatus {

    private String status;
    private Date statusUpdatedDate;
    private String statusUpdatedBy;

}
