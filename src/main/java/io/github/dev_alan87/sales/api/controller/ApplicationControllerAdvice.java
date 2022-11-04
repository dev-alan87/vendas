package io.github.dev_alan87.sales.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.dev_alan87.sales.service.impl.PurchaseNotFoundException;
import io.github.dev_alan87.sales.service.impl.RuleExcepcion;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuleExcepcion.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleRuleException(RuleExcepcion ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiError handlePurchaseNotFoundException(PurchaseNotFoundException ex) {
        return new ApiError(ex.getMessage());
    }

}