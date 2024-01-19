package com.datingfood.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq")
    @SequenceGenerator(name = "match_seq", sequenceName = "match_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    private Person partner;

    public Match() {
    }

    public Match(Person person, Person partner) {
        this.person = person;
        this.partner = partner;
    }
}
