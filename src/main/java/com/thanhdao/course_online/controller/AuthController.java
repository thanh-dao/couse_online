package com.thanhdao.course_online.controller;

import com.thanhdao.course_online.anotations.V1RestController;
import com.thanhdao.course_online.dto.user.request.RegisterRequest;
import com.thanhdao.course_online.entity.User;
import com.thanhdao.course_online.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@V1RestController("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping(value = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@ModelAttribute @Valid RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.created(URI.create("/")).build();
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        return ResponseEntity.ok(userService.generateLoginResponse(authentication));
    }
}
