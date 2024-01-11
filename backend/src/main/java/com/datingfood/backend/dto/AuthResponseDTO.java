package com.datingfood.backend.dto;

import lombok.Data;

/**
 * returns access Token and type
 */
@Data
public class AuthResponseDTO {
   private String accessToken;
   private String tokenType = "Bearer ";

   public AuthResponseDTO(String accessToken) {
       this.accessToken = accessToken;
   }
}
