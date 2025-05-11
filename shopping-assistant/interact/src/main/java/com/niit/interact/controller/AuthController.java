package com.niit.interact.controller;

import com.niit.interact.api.DTO.AuthResponseDTO;
import com.niit.interact.api.DTO.LoginDTO;
import com.niit.interact.api.DTO.RegisterDTO;
import com.niit.interact.entity.User;
import com.niit.interact.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        User user = authService.authenticate(loginDTO);
        if (user != null) {
            String token = generateTokenForUser(user);

            AuthResponseDTO response = new AuthResponseDTO();
            response.setToken(token);
            response.setUser(user);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO registerDTO) {
        User user = authService.register(registerDTO);
        if (user != null) {
            String token = generateTokenForUser(user);

            AuthResponseDTO response = new AuthResponseDTO();
            response.setToken(token);
            response.setUser(user);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private String generateTokenForUser(User user) {

        return "token_" + user.getId();
    }
}
