package com.datingfood.backend.api;

import com.datingfood.backend.dto.AuthResponseDTO;
import com.datingfood.backend.dto.LoginDTO;
import com.datingfood.backend.dto.RegisterDTO;
import com.datingfood.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = authService.authenticatePerson(loginDTO);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("registration")
    public ResponseEntity<Void> register(@RequestBody final RegisterDTO registerDTO) {
        try {
            authService.registerPerson(registerDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
