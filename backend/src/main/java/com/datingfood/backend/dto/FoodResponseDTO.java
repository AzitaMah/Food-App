package com.datingfood.backend.dto;


import lombok.Data;

@Data
public class FoodResponseDTO {

    private int id;
    private String name;

    public FoodResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
