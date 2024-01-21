package com.datingfood.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private LocalDate birthdate;
    private String contact;
    private String profileimage;


}
