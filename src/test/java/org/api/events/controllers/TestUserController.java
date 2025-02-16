package org.api.events.controllers;

import org.api.events.constents.VerficationState;
import org.api.events.models.User;
import org.api.events.service.userservice.IUserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class TestUserController {
    private static final Logger log = LoggerFactory.getLogger(TestUserController.class);
    //  log.info("\u001B[1;31m :: Testing API  "+email +"  :: \u001B[0m");  red


    @MockBean
    private IUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testUserControllerGetUserByEmail() throws Exception {
       final String email = "jogayogeshedu@gmail.com";
       log.info("\u001B[1;31m :: USER CONTROLLER Testing API  "+email +"  :: \u001B[0m");
       // user object created
        User user = new User();
        user.setEmail(email);
        user.setId(UUID.randomUUID());
        user.setUserName("jogayogesh");
        user.setPassword("jogayogesh@1234567");
        user.setState(VerficationState.VERFICATION_COMPLETED);


        // mocked data
       when(userService.getUserByEmail(email)).thenReturn(user);

       mockMvc.perform(get("/user/getuser/{email}", email)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.email").value(email))
               // user always return the String only so must compare with string only
               .andExpect(jsonPath("$.id").value(user.getId().toString()))
               .andExpect(jsonPath("$.password").value(user.getPassword()));
    }
}
