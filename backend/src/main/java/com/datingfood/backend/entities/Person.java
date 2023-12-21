package com.datingfood.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;

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
    private LocalDate birthDate;

    @Column(nullable = false)
    @JsonIgnore
    private Role role;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    //@JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    public Person() {
    }

    public Person(final String username, final String firstName, final String lastName, final String contact, final LocalDate birthDate, final String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.birthDate = birthDate;
        this.password = password;
    }


    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
