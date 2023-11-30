package com.example.java_train.Models.AuthModel;

import com.example.java_train.Entities.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterModel {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}
