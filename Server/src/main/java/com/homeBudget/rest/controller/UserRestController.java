package com.homeBudget.rest.controller;

import com.homeBudget.configuration.JsonWebTokens.JWTLoginSucessReponse;
import com.homeBudget.configuration.JsonWebTokens.JwtTokenProvider;
import com.homeBudget.configuration.LoginRequest;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.homeBudget.configuration.MapValidationErrorService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static com.homeBudget.configuration.security.SecurityConstants.TOKEN_PREFIX;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PropertySource("classpath:messages.properties")
@Validated
@CrossOrigin
public class UserRestController {

    private final UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }

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
