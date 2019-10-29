package com.homeBudget.rest.controller;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PropertySource("classpath:messages.properties")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity registerUserAccount(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.registerUserAccount(user));
    }

    @GetMapping
    public ResponseEntity getUser(){
        return ResponseEntity.ok(new UserDTO(userService.getUserByAuthentication()));
    }

    @PatchMapping
    public ResponseEntity changingUserData(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.changingUserData(userDTO, userService.getEmailByAuthentication()));
    }
}
