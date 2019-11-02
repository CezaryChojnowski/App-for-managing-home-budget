package com.homebudget.domain.user

import com.homeBudget.domain.user.User
import com.homeBudget.domain.user.UserRepository
import com.homeBudget.domain.user.UserService
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification
@DataJpaTest
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
        User user = new User(id,firstName,lastName,password,email)
        when:
        userRepository.save(_ as User) >> user
        User newUser = userService.createUser(firstName,lastName,password,email)
        then:
        user.equals(newUser)
    }
}
