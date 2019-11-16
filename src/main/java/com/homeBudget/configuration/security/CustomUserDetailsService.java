package com.homeBudget.configuration.security;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).get();
        if (user == null) new UsernameNotFoundException("User not found");
        return user;
    }


    @Transactional
    public User loadUserById(int id) {
        User user = userRepository.findById(id).get();
        if (user == null) new UsernameNotFoundException("User not found");
        return user;

    }
}
