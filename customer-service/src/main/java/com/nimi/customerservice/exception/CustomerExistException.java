package com.nimi.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerExistException extends RuntimeException{
    public CustomerExistException(String message) {
        super(message);
    }
}
