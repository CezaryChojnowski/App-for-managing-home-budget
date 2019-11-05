package com.homeBudget.rest.controller;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PropertySource("classpath:messages.properties")
@Validated
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

    public ResponseEntity changingUserData(@NotNull(message = "{firstName.message.empty}") @RequestParam(defaultValue = " ") String firstName,
                                           @NotNull(message = "{lastName.message.empty}") @RequestParam(defaultValue = " ") String lastName){
        return ResponseEntity.ok(userService.changingUserData(firstName, lastName, userService.getEmailByAuthentication()));
    }

    @PatchMapping("/email")
    public ResponseEntity changingEmail(@Email(message = "{email.message.incorrect}") @RequestParam String newEmail){
        return ResponseEntity.ok(userService.changingEmail(newEmail, userService.getEmailByAuthentication()));
    }
}
