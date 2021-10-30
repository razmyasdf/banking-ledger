package com.nimi.ledgerservice.exception;

public class NotEnoughFundException extends RuntimeException{
    public NotEnoughFundException(String message) {
        super(message);
    }
}
