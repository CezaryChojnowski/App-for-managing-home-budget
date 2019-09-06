package com.rest;

import com.dao.UserDAO;
import com.entity.User;
import com.error.RecordExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserDAO userDAO;
    private final Environment env;

    @PostMapping
    public void registerUserAccount(@Valid @RequestBody User user){
        userDAO.beginTransaction();
        if(userDAO.checkIfUserWithGivenEmailExists(user.getEmail())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + user.getEmail());
        }
            userDAO.saveUser(user.getFirstName(),user.getLastName(), user.getPassword(), user.getEmail());
    }
}
