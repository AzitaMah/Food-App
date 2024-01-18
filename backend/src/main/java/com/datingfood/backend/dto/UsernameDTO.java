package com.datingfood.backend.dto;

import lombok.Data;

@Data
public class UsernameDTO {

    private String username;

    public UsernameDTO() {
    }

    public UsernameDTO(String username) {
        this.username = username;
    }

}
