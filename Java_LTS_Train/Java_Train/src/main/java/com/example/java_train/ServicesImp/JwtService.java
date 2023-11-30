package com.example.java_train.ServicesImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.Map;

@Service
public class JwtService {
    private String secretKey =  "AKDJANVADSNLKAMSKDSMALKDMLSAKNDJKQWNE123141KASMLKEQWMLF";

    private long jwtExpiration = 864000;

    private long refreshExpiration = 6048000;

    // lấy thông tin user trong token
    public String ExtractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = ExtractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // lấy toàn bộ claim(Username, secretkey, ..) từ token
    private Claims ExtractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(GetSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Tạo access token từ userDetails (Bỏ cũng đc)
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // tạo access token từ extra claims và userDetails
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        //extraClaims.put("email", userDetails.getAuthorities());
        // có thể thêm nhiều thông tin hơn vào phần extra claim của token
        // nếu không tìm thấy getEmail() -> define thêm bên trong accountRepository
        return BuildToken(extraClaims, userDetails, jwtExpiration);
    }

    // tạo refresh token
    public String GenerateRefreshToken(UserDetails userDetails) {
        return BuildToken(new HashMap<>(), userDetails, refreshExpiration);
    }


    // Build token từ claims, userdetails, date
    private String BuildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(GetSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // lấy secrect key đã bị mã hóa
    private Key GetSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // kiểm tra token có hợp lệ ko (so sánh username trong token + thời hạn của token)
    public boolean IsTokenValid(String token, UserDetails userDetails) {
        final String username = ExtractUsername(token);
        return (username.equals(userDetails.getUsername()) && !IsTokenExpired(token));
    }

    private boolean IsTokenExpired(String token) {
        return ExtractExpiration(token).before(new Date());
    }

    private Date ExtractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
