package com.example.Gifts;

import com.example.Gifts.exception.gift.GiftIdAlreadyExistException;
import com.example.Gifts.exception.gift.GiftNotFoundException;
import com.example.Gifts.model.Gift;
import com.example.Gifts.service.GiftService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GiftServiceTest {
    private static final Long ID = 1L;
    private static final Long OWNER_ID = 1L;
    private static final Long SENDER_ID = 1L;
    private static final String MESSAGE = "Message";

    @BeforeEach
    @AfterEach
    void checkGiftDataBaseEmpty() {
        assertTrue(GiftService.giftDataBase.isEmpty());
    }


    @Test
    void shouldAddGift() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));

        assertEquals(1,GiftService.giftDataBase.size());

        Gift gift = GiftService.giftDataBase.get(0);

        assertEquals(ID,gift.getId());
        assertEquals(OWNER_ID,gift.getOwnerId());
        assertEquals(SENDER_ID,gift.getSenderId());
        assertEquals(MESSAGE,gift.getMessage());

        GiftService.giftDataBase.remove(0);

    }
    @Test
    void shouldGetGift() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));
        Gift gift = GiftService.giftDataBase.get(0);
        assertNotNull(gift);

        assertEquals(ID,gift.getId());
        assertEquals(OWNER_ID,gift.getOwnerId());
        assertEquals(SENDER_ID,gift.getSenderId());
        assertEquals(MESSAGE,gift.getMessage());

        GiftService.giftDataBase.remove(0);
    }

    @Test
    void shouldDeleteGift() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));
        Gift gift = GiftService.giftDataBase.get(0);
        assertNotNull(gift);

        GiftService.deleteGift(ID.intValue());

        assertEquals(0,GiftService.giftDataBase.size());
    }

    @Test
    void shouldUpdateGift() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));
        Gift gift = GiftService.giftDataBase.get(0);
        assertNotNull(gift);

        String newMessage = "New message";
        GiftService.updateGiftMessage(ID.intValue(), newMessage);

        Gift currentGift = GiftService.giftDataBase.get(0);

        assertEquals(currentGift.getId(),gift.getId());
        assertEquals(currentGift.getOwnerId(),gift.getOwnerId());
        assertEquals(currentGift.getSenderId(),gift.getSenderId());
        assertEquals(newMessage,gift.getMessage());

        GiftService.giftDataBase.remove(0);
    }

    @Test
    void shouldDeleteThrowGiftNotFoundException() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));

        Long newId = 2L;

        assertThrows(GiftNotFoundException.class,
                () -> GiftService.deleteGift(newId.intValue()),
                "shouldDeleteThrowGiftNotFoundException");
        GiftService.giftDataBase.remove(0);

    }

    @Test
    void shouldUpdateThrowGiftNotFoundException() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));

        Long newId = 2L;
        String updateMessage = "NewMessage";

        assertThrows(GiftNotFoundException.class,
                () -> GiftService.updateGift(new Gift(newId, OWNER_ID, SENDER_ID, updateMessage)),
                    "shouldUpdateThrowGiftNotFoundException");

        GiftService.giftDataBase.remove(0);
    }

    @Test
    void shouldAddThrowGiftIdAlreadyExistException() {
        GiftService.giftDataBase.add(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE));

        assertThrows(GiftIdAlreadyExistException.class,
                () -> GiftService.sendGift(new Gift(ID,OWNER_ID,SENDER_ID,MESSAGE)),
                "shouldAddThrowGiftIdAlreadyExistException");

        GiftService.giftDataBase.remove(0);
    }
}
