package com.my.snacting.domain.user.controller;

import com.my.snacting.domain.user.dto.request.UserCreateRequest;
import com.my.snacting.domain.user.dto.response.UserCreateResponse;
import com.my.snacting.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User API", description = "유저 관련 API")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저 생성")
    public UserCreateResponse createUser(
            @Valid @RequestBody UserCreateRequest request
            ) {
        return userService.createUser(request);
    }
}
