package com.rest;

import com.dao.UserDAO;
import com.entity.User;
import com.error.Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


// pro tip - use ctr+alt+l and use ctr+alt+o



@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PropertySource("classpath:messages.properties")
public class UserRestController {

    private final UserDAO userDAO;
    private final Environment env;

    @GetMapping
    //Are you sure that you want to return user with his password in response?
    //create UserDto - it is not good idea to return whole entity object
    public List<User> getAllUsers() {
        return userDAO.findAllUsers();
    }

    @PostMapping
    //create UserForm class
    public ResponseEntity registerUserAccount(@Valid @RequestBody User user) {
        if (userDAO.isEmailExists(user.getEmail())) {
            throw new Exception(env.getProperty("recordExist") + " " + user.getEmail());
        } else {
            //return DTO
            return ResponseEntity.ok(userDAO.createUser(user));
        }
    }
}
