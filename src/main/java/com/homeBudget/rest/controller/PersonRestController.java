package com.homeBudget.rest.controller;

import com.homeBudget.domain.person.PersonService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonRestController {

    public final PersonService personService;
    public final UserService userService;

    @GetMapping
    public ResponseEntity getAllPersonsByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(personService.findPersonsByUser(user));
    }
}
