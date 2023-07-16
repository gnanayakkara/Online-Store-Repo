package com.kidletgift.product.advice;

import com.kidletgift.product.exception.GiftItemNotFoundException;
import com.kidletgift.product.exception.GiftItemSaveOrUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(GiftItemSaveOrUpdateException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(GiftItemSaveOrUpdateException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GiftItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(GiftItemNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
