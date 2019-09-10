package com.service;

import com.User.User;
import com.User.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        User user = userDAO.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked,
                getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return authorities;
    }
}