package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.factory.RegisterCommandFactory;
import com.modoodesigner.factory.WithUser;
import com.modoodesigner.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {PendantApiController.class})
@Slf4j
class PendantApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PendantService service;

    @Test
    @SneakyThrows
    @WithUser("admin")
    void register_blankPayload_shouldFailAndReturn400() {
        mvc.perform(getPendantPost())
                .andExpect(status().is(400));
    }

    @Test
    @SneakyThrows
    @WithUser(value = "user")
    void register_payloadWithNotAdmin_shouldFailAndReturn403() {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        mvc.perform(getPendantPost()
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(command)))
                .andExpect(status().is(403));
    }

    @Test
    @SneakyThrows
    @WithUser(value = "admin")
    void register_existedPendantPayload_shouldFailAndReturn400() {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();

        doNothing().when(service)
                .register(command);

        mvc.perform(getPendantPost()
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(command)))
                .andExpect(status().is(201));
    }


    private MockHttpServletRequestBuilder getPendantPost() {
        return post("/api/pendants");
    }


}