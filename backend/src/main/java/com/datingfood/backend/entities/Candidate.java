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
    @Column
    private String username;

    public Candidate() {
    }

    public void setId(final Long id) {
        this.id = id;
    }

}
