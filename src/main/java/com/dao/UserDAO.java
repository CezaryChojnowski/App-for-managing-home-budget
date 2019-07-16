package com.dao;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserDAO {

    private UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    private User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public boolean checkTheUniqueEmail(String email){
        return findUserByEmail(email) == null;
    }
}
