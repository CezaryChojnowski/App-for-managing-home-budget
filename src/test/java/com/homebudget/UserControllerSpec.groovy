package com.homebudget

import com.AppForManagingHomeBudgetApplication
import com.fasterxml.jackson.databind.ObjectMapper
import com.homeBudget.domain.User.User
import com.homeBudget.domain.User.UserDAO
import com.homeBudget.domain.User.UserRepository
import com.homeBudget.rest.UserRestController
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ContextConfiguration(classes = AppForManagingHomeBudgetApplication.class)
@ComponentScan("com.homeBudget.domain.User")
class UserControllerSpec extends Specification {

    UserRestController userRestController
    UserDAO userDAO
    UserRepository userRepository
    Environment environment
    MockMvc mockMvc
    ObjectMapper objectMapper = new ObjectMapper()

    User user
    String userJsonString


    def setup(){
        userRepository = Mock(UserRepository)
        userDAO = Mock(UserDAO)
        userRestController = new UserRestController(userDAO,environment)
        mockMvc = MockMvcBuilders
                .standaloneSetup(userRestController)
                .alwaysDo(MockMvcResultHandlers
                        .print())
                .build()
    }
    @Sql(scripts = "/data-test.sql", executionPhase = BEFORE_TEST_METHOD)
    void 'Test'(){
        given:
        user = new User([firstName: 'Cezary', lastName: 'Chojnowski', password: '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu', email: 'temp1@gmail.com'])
        userJsonString = objectMapper.writeValueAsString(user)
        expect:
        mockMvc.perform(MockMvcRequestBuilders.
                post('/users').
                contentType(MediaType.
                        APPLICATION_JSON).
                content(userJsonString)).
                andExpect(MockMvcResultMatchers.
                        status().
                        is4xxClientError())
    }

}
