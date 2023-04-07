package com.example.Gifts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String login;

}
