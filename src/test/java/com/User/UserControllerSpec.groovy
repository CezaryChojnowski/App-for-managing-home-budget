package com.User

import com.AppForManagingHomeBudgetApplication
import com.fasterxml.jackson.databind.ObjectMapper
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

//packages in tests should be the same as in production code
//if you test UserController class from package com.User
// you should create UserControllerSpec class also in package com.User
@ContextConfiguration(classes = AppForManagingHomeBudgetApplication.class)
@ComponentScan("com.User")
class UserControllerSpec extends Specification {

    def userDAO = Mock(UserDAO)
    def userRepository = Mock(UserRepository)
    def environment = Mock(environment)
    MockMvc mockMvc
    ObjectMapper objectMapper = new ObjectMapper()

    User user
    String userJsonString


    //if you want to mock some stuff you dont need setup
    // you can just change everything as i did with def userRepository = Mock(UserRepository)

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
