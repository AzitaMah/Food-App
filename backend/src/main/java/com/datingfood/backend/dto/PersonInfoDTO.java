package com.datingfood.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonInfoDTO {
    private String username;
    private String profileimage;
    private LocalDate birthdate;


    public PersonInfoDTO() {
    }

    public PersonInfoDTO(String username,String profileimage, LocalDate birthdate) {
        this.username = username;
        this.profileimage = profileimage;
        this.birthdate = birthdate;
    }
}
