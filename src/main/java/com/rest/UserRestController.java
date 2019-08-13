package com.rest;

import com.dao.UserDAO;
import com.entity.User;
import com.error.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@PropertySource("classpath:messages.properties")
public class UserRestController {

    private UserDAO userDAO;

    private Environment env;

    @Autowired
    public UserRestController(UserDAO theUserDAO, Environment theEnv){
        userDAO = theUserDAO;
        env = theEnv;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userDAO.findAllUsers();
    }

    @PostMapping(value="/register")
    public ResponseEntity registerUserAccount(@Valid @RequestBody User user){
        if(!userDAO.checkTheUniqueEmail(user.getEmail())){
            throw new Exception(env.getProperty("recordExist") + " " + user.getEmail());
        }
        else{
            return ResponseEntity.ok(userDAO.createUser(user));
        }
    }
}
