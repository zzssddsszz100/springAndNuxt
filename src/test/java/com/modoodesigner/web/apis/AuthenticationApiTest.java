package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.impl.UserServiceImpl;
import com.modoodesigner.domain.model.user.User;
import com.modoodesigner.web.payload.RegistrationPayload;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class AuthenticationApiTest {

    private MockMvc mvc;
    @Autowired WebApplicationContext context;
    @Autowired
    UserServiceImpl userService;


    @Test
    void login() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("testName");
        payload.setEmailAddress("test@test.com");
        payload.setPassword("MyPassword!@");

        userService.register(payload.toCommand());
        User testName = userService.findByUsername("testName");

        assertEquals(testName.getUsername(), payload.getUsername());
        assertNotNull(testName.getId());



        JSONObject object = new JSONObject().put("email", "testName").put("password", "MyPassword!@");

        mvc.perform(post("/api/authentications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object.toString())
        ).andExpect(status().isOk());
    }
}
