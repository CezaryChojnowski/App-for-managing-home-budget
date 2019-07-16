package com.rest;

import com.dao.UserDAO;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserDAO userDAO;

    @Autowired
    public UserRestController(UserDAO theUserDAO){
        userDAO = theUserDAO;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userDAO.findAllUsers();
    }

    @PostMapping(value="/register")
    public ResponseEntity registerUserAccount(@RequestBody User user){
        return ResponseEntity.ok(userDAO.createUser(user));
    }
}
