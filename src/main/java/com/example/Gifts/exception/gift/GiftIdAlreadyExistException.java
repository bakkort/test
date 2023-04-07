package com.example.Gifts.exception.gift;

public class GiftIdAlreadyExistException extends RuntimeException{
    public GiftIdAlreadyExistException(String message) {
        super(message);
    }
}
