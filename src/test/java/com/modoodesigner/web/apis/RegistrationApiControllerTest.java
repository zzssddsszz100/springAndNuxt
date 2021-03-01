package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.UserService;
import com.modoodesigner.domain.model.user.UsernameExistsException;
import com.modoodesigner.utils.JsonUtils;
import com.modoodesigner.web.payload.RegistrationPayload;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {RegistrationApiController.class})
class RegistrationApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService serviceMock;

    @BeforeAll
    static void setup(){

    }

    @Test
    void register_blankPayload_shouldFailAndReturn400() throws Exception {
        mvc.perform(post("/api/registrations"))
                .andExpect(status().is(400));
    }

    @Test
    void register_existedUsername_shouldFailAndReturn400() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("test@test.com");
        payload.setPassword("MyPassword!@");

        Mockito.doThrow(UsernameExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("유저이름이 이미 등록되어 있습니다."));

    }

    @Test
    void register_validPayload_shouldSucceedAndReturn201() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("testName");
        payload.setEmailAddress("test@test.com");
        payload.setPassword("MyPassword!@");

        doNothing().when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(201));
    }


}