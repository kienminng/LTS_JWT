package com.example.java_train.ServicesImp;

import com.example.java_train.Entities.Account;
import com.example.java_train.Entities.Enum.TokenType;
import com.example.java_train.Entities.Token;
import com.example.java_train.Models.AuthModel.LoginModel;
import com.example.java_train.Models.AuthModel.RegisterModel;
import com.example.java_train.Models.Response.LoginResponseModel;
import com.example.java_train.Repositories.AccountRepository;
import com.example.java_train.Repositories.TokenRepository;
import com.example.java_train.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private AccountRepository accountReposiroty;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public LoginResponseModel Register(RegisterModel registerModel) {
        var account = Account.builder()
                .firstname(registerModel.getFirstname())
                .lastname(registerModel.getLastname())
                .email(registerModel.getEmail())
                .password(passwordEncoder.encode(registerModel.getPassword()))
                .role(registerModel.getRole())
                .build();

        var savedAccount = accountReposiroty.save(account);
        var refreshToken = jwtService.GenerateRefreshToken(savedAccount);
        var accessToken = jwtService.generateToken(savedAccount);
        SaveAccountRefreshToken(savedAccount, refreshToken);
        return LoginResponseModel.builder()
                .AccessToken(accessToken)
                .RefreshToken(refreshToken)
                .build();
    }

    @Override
    public ResponseEntity<LoginResponseModel> Login(LoginModel loginModel) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponseModel> RefreshToken() {
        return null;
    }

    private void SaveAccountRefreshToken(Account account, String refreshToken) {
        var token = Token.builder()
                .account(account)
                .token(refreshToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
