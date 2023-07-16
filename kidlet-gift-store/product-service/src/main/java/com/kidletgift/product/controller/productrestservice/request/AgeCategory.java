package com.kidletgift.product.controller.productrestservice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AgeCategory {

    private Integer from;
    private Integer to;
}
