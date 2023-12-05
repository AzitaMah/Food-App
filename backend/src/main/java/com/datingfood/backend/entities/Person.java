package com.datingfood.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Role role;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String userName;

    public Person() {
    }

    public Person(String userName, String firstName, String lastName, String contact, LocalDate birthDate, Role role, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.birthDate = birthDate;
        this.role = role;
        this.password = password;

    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setuUserName(String uId) {
        this.userName = uId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}