package com.repository;

import com.entity.User;

import java.util.Optional;

public interface UserRepository{

    void saveUser(String firstName, String lastName, String password, String email);

    void deleteUser(String email);

    Optional<User> findUserByEmail(String email);

    void updateUser(User employee);

}
