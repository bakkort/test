package com.example.Gifts.exception.gift;

public class GiftNotFoundException extends RuntimeException{
    public GiftNotFoundException(String message) {
        super(message);
    }
}
