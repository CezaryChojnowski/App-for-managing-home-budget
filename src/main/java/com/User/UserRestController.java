package com.User;

import com.error.RecordExistsException;
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

    private final UserDAO userDAO;
    private final Environment env;

    @PostMapping
    public ResponseEntity registerUserAccount(@Valid @RequestBody User user) {
        if (userDAO.isEmailExists(user.getEmail())) {
            throw new RecordExistsException(env.getProperty("recordExists") + " " + user.getEmail());
        } else {
            userDAO.createUser(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
            return ResponseEntity.ok(userDAO.convertToDto(user));
        }
    }

    @GetMapping
    public ResponseEntity getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        return ResponseEntity.ok(userDAO.convertToDto(user));
    }

    @PatchMapping
    public ResponseEntity changingUserData(@Valid @RequestBody UserDTO userDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userDAO.changingUserData(userDTO, email);
        return ResponseEntity.ok(userDTO);
    }
}
