package com.dao;

import com.entity.User;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean isEmailExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    //get all required fields as arguments or some form class
    public User createUser(User user) {
        //create user here by constructor, do not use setter
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setWallets(new ArrayList<>());
        return userRepository.save(user);
    }

    public User findUserByID(Integer userID) {
        //create some specific Exception
        return userRepository.findUserById(userID).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
