package io.github.dev_alan87.sales.api.controller;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiError {

    @Getter
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }
    
    public ApiError(String message) {
        this.errors = Arrays.asList(message);
    }

}
