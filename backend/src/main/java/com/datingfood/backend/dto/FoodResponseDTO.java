package com.datingfood.backend.dto;


import lombok.Data;

@Data
public class FoodResponseDTO {

    private int id;
    private String name;
    private String imageBase64;

    public FoodResponseDTO(final int id, final String name, final String imageBase64) {
        this.id = id;
        this.name = name;
        this.imageBase64 = imageBase64;
    }

}
