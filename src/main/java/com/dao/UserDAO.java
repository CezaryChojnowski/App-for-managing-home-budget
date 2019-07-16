package com.dao;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

    public User createUser(String login, String password, String email){
        User user = new User();
        user.setLogin(login);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        return userRepository.save(user);
    }
}
