package com.ng.medapi.Controllers;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ng.medapi.Dtos.AuthResponseDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String rol = "UNKNOWN";
        if (request.isUserInRole("ADMIN")) {
            rol = "ADMIN";
        } else if (request.isUserInRole("USER")) {
            rol = "USER";
        }

        AuthResponseDTO response = new AuthResponseDTO(principal.getName(), rol);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return ResponseEntity.ok().build();
    }
}
