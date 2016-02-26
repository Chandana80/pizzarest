package com.ojass.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by davulc01 on 2/25/2016.
 */

public class PizzaError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public PizzaError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public PizzaError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}