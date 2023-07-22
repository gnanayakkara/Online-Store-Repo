package com.kidletgift.order.model.orderdoc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String name;
    private String streetNumberAndName;
    private String apartmentOrUnitAndNumber;
    private String buildingName;
    private String city;
    private Integer zipCode;
}
