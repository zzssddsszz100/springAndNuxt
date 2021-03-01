package com.modoodesigner.web.apis.authenticate;

import com.modoodesigner.utils.JsonUtils;
import com.modoodesigner.web.results.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        ApiResult failure;
        if (exception instanceof BadCredentialsException) {
            failure = ApiResult.message("잘못된 인증입니다.");
        } else if (exception instanceof InsufficientAuthenticationException) {
            failure = ApiResult.message("신뢰할 수 없거나 잘못된 인증 요청입니다.");
        } else {
            failure = ApiResult.message("인증 실패입니다.");
        }
        JsonUtils.write(response.getWriter(), failure);
    }
}
