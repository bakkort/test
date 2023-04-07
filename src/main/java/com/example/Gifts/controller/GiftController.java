package com.example.Gifts.controller;

import com.example.Gifts.model.Gift;
import com.example.Gifts.service.GiftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gift")
public class GiftController {
    @GetMapping("/")
    public List<Gift> getAllGift() {
        return GiftService.giftDataBase;
    }

    @GetMapping("/{id}")
    public Gift getGift(@PathVariable int id) {
        return GiftService.getGift(id);
    }

    @GetMapping("/getFromUser/{id}")
    public List<Gift> getGiftFromUser(@PathVariable int id) {
        return GiftService.getGiftFromUser(id);
    }

    @GetMapping("/getForUser/{id}")
    public List<Gift> getGiftForUser(@PathVariable int id) {

        return GiftService.getGiftForUser(id);
    }

    @PostMapping("/createGift")
    public Gift createGift(@RequestBody Gift gift) {
        List<Gift> giftList = GiftService.sendGift(gift);
        return giftList.get(giftList.size() - 1);
    }

    @PutMapping("/updateGift")
    public List<Gift> updateGift(@RequestBody Gift gift) {
        return GiftService.updateGift(gift);
    }

    @DeleteMapping("/deleteGift/{id}")
    public List<Gift> deleteGift(@PathVariable int id) {
        return GiftService.deleteGift(id);
    }
}
