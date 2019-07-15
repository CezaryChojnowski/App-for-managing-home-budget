package com.rest;

import com.dao.UserDAO;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
