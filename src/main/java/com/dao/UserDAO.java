package com.dao;

import com.entity.User;
import com.entity.Wallet;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDAO {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDAO(UserRepository theUserRepository, BCryptPasswordEncoder ThebCryptPasswordEncoder){
        userRepository = theUserRepository;
        bCryptPasswordEncoder = ThebCryptPasswordEncoder;
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

    public User createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setWallets(new ArrayList<>());
        return userRepository.save(user);
    }
}
