package com.datingfood.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Food() {
    }

    public Food(final String name) {
        this.name = name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setId(final Long id) {
        this.id = id;
    }

}
