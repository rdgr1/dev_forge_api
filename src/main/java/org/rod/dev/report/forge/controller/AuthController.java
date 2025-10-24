package org.rod.dev.report.forge.controller;

import org.rod.dev.report.forge.dto.LoginRequest;
import org.rod.dev.report.forge.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var rp = service.login(request);
        if (rp != null) {
            return ResponseEntity.ok(rp);
        }
        return ResponseEntity.badRequest().build();
    }


}

