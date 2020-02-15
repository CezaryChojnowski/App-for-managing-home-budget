//package com.homebudget.domain.user
//
//import com.homeBudget.domain.user.User
//import com.homeBudget.domain.user.UserRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
//import spock.lang.Specification
//
//@DataJpaTest
//class UserRepositorySpec extends Specification {
//    @Autowired
//    TestEntityManager testEntityManager
//    @Autowired
//    UserRepository userRepository
//
//    def 'Should return true when trying find user by email'(){
//        given: 'User with given fields'
//        def id = 3
//        def firstName = "testUserFirstName"
//        def lastName = "testUserLastName"
//        def password = "testUserPassword"
//        def email = "testUserEmail@gmail.com"
//        when:
//        testEntityManager.merge(new User(id, firstName, lastName, password, email))
//        then:
//        userRepository.findUserByEmail(email).isPresent()
//    }
//}
