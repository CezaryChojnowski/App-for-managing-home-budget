package com.homeBudget.rest.controller;

import com.homeBudget.domain.person.Person;
import com.homeBudget.domain.person.PersonService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonRestController {

    public final PersonService personService;
    public final UserService userService;

    @GetMapping
    public ResponseEntity getPersonsByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(personService.getPersons(user));
    }

    @PostMapping ResponseEntity createPerson(@Valid @RequestBody Person person){
        return ResponseEntity.ok(personService.createPerson(person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhoneNumber(), userService.getEmailByAuthentication()));
    }
}
