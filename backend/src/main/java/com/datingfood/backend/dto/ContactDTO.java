package com.datingfood.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactDTO {
    private String username;
    private String firstname;
    private String profileimage;
    private LocalDate birthdate;
    private String contact;

    public ContactDTO() {
    }

    public ContactDTO(String username, String firstname,String profileimage,LocalDate birthdate, String contact) {
        this.username =username;
        this.firstname = firstname;
        this.profileimage=profileimage;
        this.birthdate=birthdate;
        this.contact = contact;
    }
}
