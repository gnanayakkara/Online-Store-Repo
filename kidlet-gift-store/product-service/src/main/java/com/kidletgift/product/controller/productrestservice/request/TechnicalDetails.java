package com.kidletgift.product.controller.productrestservice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TechnicalDetails {

    private String key;
    private String value;
}
