package com.example.java_train.Controller;

import com.example.java_train.Models.AuthModel.RegisterModel;
import com.example.java_train.Models.Response.LoginResponseModel;
import com.example.java_train.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponseModel> register(
            @RequestBody RegisterModel registerModel
    ) {
        return ResponseEntity.ok(authService.Register(registerModel));
    }
}
