package com.homeBudget.rest.controller;

import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PropertySource("classpath:messages.properties")
public class UserRestController {

    private final UserService userService;
    private final Environment env;

    @PostMapping
    public ResponseEntity registerUserAccount(@Valid @RequestBody User user) {
        if (userService.isEmailExists(user.getEmail())) {
            throw new RecordExistsException(env.getProperty("recordExists") + " " + user.getEmail());
        } else {
            userService.createUser(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
            return ResponseEntity.ok(userService.convertToDto(user));
        }
    }

    @GetMapping
    public ResponseEntity getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(userService.convertToDto(user));
    }

    @PatchMapping
    public ResponseEntity changingUserData(@Valid @RequestBody UserDTO userDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changingUserData(userDTO, email);
        return ResponseEntity.ok(userDTO);
    }
}
