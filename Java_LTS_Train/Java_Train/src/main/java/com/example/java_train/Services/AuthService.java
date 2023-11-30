package com.example.java_train.Services;

import com.example.java_train.Entities.Account;
import com.example.java_train.Models.AuthModel.LoginModel;
import com.example.java_train.Models.AuthModel.RegisterModel;
import com.example.java_train.Models.Response.LoginResponseModel;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    LoginResponseModel Register(RegisterModel registerModel);
    ResponseEntity<LoginResponseModel> Login(LoginModel loginModel);

    ResponseEntity<LoginResponseModel> RefreshToken();
}
