package com.example.java_train.Config;

import com.example.java_train.Repositories.TokenRepository;
import com.example.java_train.ServicesImp.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component  // đánh dấu class là 1 spring bean được quản lý bởi container IoC cua Spring
@RequiredArgsConstructor // tạo ctor chứa các trường dữ lệu = key final
public class JwtAuthenticantionFillter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;


    // kiểm tra header của request để lấy token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        // AuthHeader có token (Bearer)-> get token + đọc token -> lấy username
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userName = jwtService.ExtractUsername(token);
        }

        // Username được tìm thấy
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            var isTokenValid = jwtService.IsTokenValid(token, userDetails);


            // Nếu token hợp lệ -> tạo authenticationToken chứa thông tin user -> lưu vào SecurityContextHolder : đánh dấu đã xác thực
            if(isTokenValid) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Chuyển request đến các fillter tiếp theo
        filterChain.doFilter(request, response);
    }
}
