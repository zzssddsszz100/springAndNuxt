package com.modoodesigner.domain.common.security;

import com.modoodesigner.domain.model.user.SimpleUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("접근이 거부 되었습니다. " + request.getRequestURI());
        }

        if (request.getRequestURI().startsWith("/api/")) {
            if (request.getUserPrincipal() instanceof SimpleUser) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            response.sendRedirect("/user/login");
        }
    }
}
