package com.example.Gifts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Gift {

    private Long id;
    private Long ownerId;
    private Long senderId;
    private String message;

}
