package com.datingfood.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "image_base64")
    private String imageBase64;

    public Food() {
    }
    public Food(final String name) {
        this.name = name;
    }

    public Food(int id, String name, String imageBase64) {
        this.id = id;
        this.name = name;
        this.imageBase64 = imageBase64;
    }
}
