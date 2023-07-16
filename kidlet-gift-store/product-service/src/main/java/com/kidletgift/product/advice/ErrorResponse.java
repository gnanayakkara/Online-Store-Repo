package com.kidletgift.product.advice;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class ErrorResponse {

    String errorMessage;
    String errorTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

}
