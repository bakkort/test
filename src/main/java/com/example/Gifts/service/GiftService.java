package com.example.Gifts.service;

import com.example.Gifts.exception.gift.GiftIdAlreadyExistException;
import com.example.Gifts.exception.gift.GiftNotFoundException;
import com.example.Gifts.model.Gift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GiftService {
    public static List<Gift> giftDataBase = new ArrayList<>();

    public static List<Gift> sendGift(Gift gift) throws GiftIdAlreadyExistException {
        var idUnique = giftDataBase.stream()
                .filter(s -> s.getId() == gift.getId())
                .findFirst()
                .orElse(null);

        if(idUnique == null) {
            giftDataBase.add(gift);
            return giftDataBase;
        }
        throw new GiftIdAlreadyExistException("Gift ID already exist in database");
    }

    public static Gift getGift(int id) {
        return giftDataBase.stream()
                .filter(s -> s.getId().intValue() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Gift> getGiftFromUser(int senderId) {
        return giftDataBase.stream()
                .filter(s -> s.getSenderId().intValue() == senderId)
                .collect(Collectors.toList());
    }

    public static List<Gift> getGiftForUser(int ownerId) {
        return giftDataBase.stream()
                .filter(s -> s.getOwnerId().intValue() == ownerId)
                .collect(Collectors.toList());
    }

    public static List<Gift> deleteGift(int id) throws GiftNotFoundException{
        Gift gift = getGift(id);
        if(gift != null) {
            giftDataBase.remove(gift);
            return giftDataBase;
        }
        throw new GiftNotFoundException("Gift not found in the database");
    }

    public static List<Gift> updateGift(Gift gift) throws GiftNotFoundException {
        Gift currentGift = getGift(gift.getId().intValue());
        if(currentGift != null) {
            giftDataBase.set(giftDataBase.indexOf(currentGift), gift);
            return giftDataBase;
        }
        throw new GiftNotFoundException("Gift not found in the database");
    }

    public static List<Gift> updateGiftMessage(int id, String message) throws GiftNotFoundException {
        Gift currentGift = getGift(id);
        if(currentGift != null) {
            currentGift.setMessage(message);
            return giftDataBase;
        }
        throw new GiftNotFoundException("Gift not found in the database");
    }
}
