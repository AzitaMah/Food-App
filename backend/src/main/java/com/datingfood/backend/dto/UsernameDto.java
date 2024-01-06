package com.datingfood.backend.dto;

import lombok.Data;

@Data
public class UsernameDto {

    private String username;

    public UsernameDto(String username) {
        this.username = username;
    }
}
