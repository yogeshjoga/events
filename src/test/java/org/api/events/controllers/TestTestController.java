package org.api.events.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = TestController.class)
public class TestTestController {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testGet() throws Exception {

        String userId = "usessssID";
        HttpHeaders headers = new HttpHeaders();
        headers.add("USER_ID", userId);


        mockMvc.perform(get("/test/get")
                .headers(headers));
            //    .andExpect(status().isOk());

    }
}
