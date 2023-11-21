package com.datingfood.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long foodId;
    //@Column
    //@OneToOne
    //Person person;

    public Candidate() {
    }

    public void setId(Long id) {
        this.id = id;
    }

}
