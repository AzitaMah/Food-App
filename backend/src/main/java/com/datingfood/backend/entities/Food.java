package com.datingfood.backend.entities;


import jakarta.persistence.*;
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
    public Food(String name) {
        this.name = name;
    }

    public void setName(String name) {this.name = name;}

    public void setId(Long id) {
        this.id = id;
    }


}
