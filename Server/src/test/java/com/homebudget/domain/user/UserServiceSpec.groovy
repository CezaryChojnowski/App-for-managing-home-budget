package com.homebudget.domain.user

import com.homeBudget.domain.user.User
import com.homeBudget.domain.user.UserRepository
import com.homeBudget.domain.user.UserService
import com.homeBudget.rest.dto.UserDTO
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

class UserServiceSpec extends Specification{
    UserRepository userRepository
    UserService userService
    BCryptPasswordEncoder bCryptPasswordEncoder
    Environment environment

    def setup(){
        bCryptPasswordEncoder = Mock(BCryptPasswordEncoder)
        environment = Mock(Environment)
        userRepository = Mock(UserRepository)
        userService = new UserService(userRepository, bCryptPasswordEncoder, environment)
    }
    def 'Should return user when trying induce createUser method in userService'(){
        given: 'User with all required fields'
        def id = 4
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        userRepository.save(_ as User) >> user
        User newUser = userService.createUser(firstName,lastName,password,email)
        then:
        user.equals(newUser)
    }

    def 'Should return false, when user exists'(){
        given: 'User with all required fields'
        def id = 4
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        userRepository.findUserByEmail(_ as String) >> Optional.of(user);
        boolean result = userService.isEmailExists(user.getEmail());
        then:
        result == true;
    }

    def 'Should return user with changed first name and last name'(){
        given: 'User with all required fields'
        def idBefore = 3
        def firstNameBefore = "firstNameBeforeChanging"
        def lastNameBefore = "lastNameBeforeChanging"
        def passwordBefore = "passwordBeforeChanging"
        def emailBefore = "tempEmail@temp.temp"
        def firstNameAfter = "firstNameAfterChanging"
        def lastNameAfter = "lastNameAfterChanging"
        User user = new User.UserBuilder()
                .id((Long)idBefore)
                .firstName(firstNameBefore)
                .lastName(lastNameBefore)
                .password(bCryptPasswordEncoder.encode(passwordBefore))
                .email(emailBefore)
                .idTravel((long) 0)
                .build();
        when:
        userRepository.findUserByEmail(_ as String) >> Optional.of(user)
        UserDTO userAfterChanging = userService.changingUserData(firstNameAfter, lastNameAfter, emailBefore)
        then:
        userAfterChanging.firstName.equals(firstNameAfter) && userAfterChanging.lastName.equals(lastNameAfter)
    }

    def 'Should return found user'(){
        given: 'User with all required fields'
        def id = 4
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        userRepository.findUserByEmail(_ as String) >> Optional.of(user)
        User newUser = userService.findUserByEmail(email)
        then:
        user.equals(newUser)
    }

}
