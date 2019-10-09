package com.homebudget;

import com.AppForManagingHomeBudgetApplication;
import com.User.User;
import com.User.UserDAO;
import com.User.UserRepository;
import com.User.UserRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppForManagingHomeBudgetApplication.class)
@ComponentScan("com.User")
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRestController userRestController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController)
                .build();
    }

    @Test
    @Sql(scripts = "/data-test.sql", executionPhase = BEFORE_TEST_METHOD)
    public void registrationUser() throws Exception {
        User user = new User.Builder()
                .firstName("Cezary")
                .lastName("Chojnowski")
                .password("$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu")
                .email("temp1@gmail.com")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is4xxClientError());
    }
}
