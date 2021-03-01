package com.modoodesigner.web.apis.authenticate;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationFilterTest {

    @MockBean
    private AuthenticationManager authenticationManagerMock;

    @Test
    void attemptAuthentication_emptyRequestBody_shouldFail() throws JsonParseException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications");
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerMock);
        assertThrows(InsufficientAuthenticationException.class, () ->
                filter.attemptAuthentication(request, new MockHttpServletResponse()));

    }

    @Test
    void attemptAuthentication_invalidJsonStringRequestBody_shouldFail() throws JsonParseException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications");
        request.setContent("email=testusername&password=TestPassword!@".getBytes(StandardCharsets.UTF_8));
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerMock);
        assertThrows(InsufficientAuthenticationException.class, () ->
                filter.attemptAuthentication(request, new MockHttpServletResponse()));
    }

    @Test
    void attemptAuthentication_validJsonStringRequestBody_shouldSucceed() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications");
        request.setContent("{\"email\":\"testUsername\",\"password\":\"TestPassword!@\"}".getBytes(StandardCharsets.UTF_8));
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerMock);
        filter.attemptAuthentication(request, new MockHttpServletResponse());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("testUsername", "TestPassword!@");
        Mockito.verify(authenticationManagerMock).authenticate(token);
    }

}