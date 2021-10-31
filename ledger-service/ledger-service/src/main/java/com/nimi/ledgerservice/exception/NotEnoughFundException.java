package com.nimi.ledgerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughFundException extends RuntimeException{
    public NotEnoughFundException(String message) {
        super(message);
    }
}
