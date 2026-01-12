package io.github.joaovitorleal.securecapita.controller;

import io.github.joaovitorleal.securecapita.dto.ApiResponseDto;
import io.github.joaovitorleal.securecapita.dto.UserRequestDto;
import io.github.joaovitorleal.securecapita.dto.UserResponseDto;
import io.github.joaovitorleal.securecapita.dto.form.LoginFormDto;
import io.github.joaovitorleal.securecapita.service.UserService;
import io.github.joaovitorleal.securecapita.controller.utils.UriGenerator;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody @Valid LoginFormDto loginForm) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.email(), loginForm.password()));
        UserResponseDto userResponseDto = userService.getUserByEmail(loginForm.email());
        return userResponseDto.usingMfa()
                ? this.sendVerificationCode(userResponseDto)
                : this.sendResponse(userResponseDto);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return ResponseEntity.created(UriGenerator.generate(userResponseDto.id())).body(
                ApiResponseDto.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .data(Map.of("user", userResponseDto))
                        .message("User created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    private ResponseEntity<ApiResponseDto> sendResponse(UserResponseDto userResponseDto) {
        return ResponseEntity.ok(
                ApiResponseDto.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .data(Map.of("user", userResponseDto))
                        .message("Login successful")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    private ResponseEntity<ApiResponseDto> sendVerificationCode(UserResponseDto userResponseDto) {
        userService.sendVerificationCode(userResponseDto);
        return ResponseEntity.ok(
                ApiResponseDto.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .data(Map.of("user", userResponseDto))
                        .message("Verification code sent.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}


















