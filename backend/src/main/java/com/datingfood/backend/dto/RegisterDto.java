package com.datingfood.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate birthdate;
    private String contact;


}
