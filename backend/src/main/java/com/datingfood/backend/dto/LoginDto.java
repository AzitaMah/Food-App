package com.datingfood.backend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class LoginDto {
    private String username;
    private String password;
}
