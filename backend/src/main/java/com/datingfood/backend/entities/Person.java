package com.datingfood.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;

//import java.sql.Date;
import java.time.LocalDate;

@Getter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String contact;

    public Person() {
    }

    public Person(String firstName, String lastName, String contact,LocalDate date,Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact= contact;
        this.date = date;
        this.role = role;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setContact(String contact) {this.contact = contact;}
    public void setDate(LocalDate date) {this.date = date;}
    public void setRole(Role role) {this.role = role;}
}
