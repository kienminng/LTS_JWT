package com.example.java_train.Models.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseModel {
    private String AccessToken;
    private String RefreshToken;
    //private LocalDate Expiration;
}
