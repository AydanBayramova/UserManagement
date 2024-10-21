package az.edu.turing.msaccount.controller;

import az.edu.turing.msaccount.config.CustomUserDetailsService;
import az.edu.turing.msaccount.config.JwtUtil;
import az.edu.turing.msaccount.model.dto.LoginRequest;
import az.edu.turing.msaccount.model.dto.LoginResponse;
import az.edu.turing.msaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AccountService userService;
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        String jwt = userService.login(authenticationRequest);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
