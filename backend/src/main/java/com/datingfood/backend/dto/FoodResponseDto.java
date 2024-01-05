package com.datingfood.backend.dto;


import lombok.Data;

@Data
public class FoodResponseDto {

    private int id;
    private String name;

    public FoodResponseDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
