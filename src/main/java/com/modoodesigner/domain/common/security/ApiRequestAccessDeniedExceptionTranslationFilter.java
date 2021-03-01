package com.modoodesigner.domain.common.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiRequestAccessDeniedExceptionTranslationFilter extends GenericFilterBean {

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            chain.doFilter(request, response);
        } catch (IOException ex) {
            throw ex;
        } catch (Exception ex) {
            //요청이 API 요청이 아닌 경우 예외를 다시 발생시킵니다.
            if (!request.getRequestURI().startsWith("/api/") && !request.getRequestURI().startsWith("/admin/api/") && !request.getRequestURI().startsWith("/rt/")) {
                throw ex;
            }
            //stacktrace 에서 SpringSecurityException 추출하기
            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
            RuntimeException ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);

            //스프링 시큐리티의 AccessDeniedException 이 아닙니다. 여기서 처리 할 필요가 없습니다.
            if (ase == null) {
                throw ex;
            }

            if (response.isCommitted()) {
                throw new ServletException("AccessDeniedException 이 이미 커밋되었기때문에 변경 불가능합니다.");
            }
            
            // 사용자가 인증되지 않았습니다. 403 오류를 표시하는 대신
            // 클라이언트에 401 오류를 전송하여 요청 된
            // 리소스에는 인증이 필요하며 클라이언트는 인증되지 않았습니다.
            // 401은 익명의 사용자, 403은 권한이 없는 로그인유저
            // 참조 : https://httpstatuses.com/
            if (request.getUserPrincipal() == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }




        }
    }

    private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {

        protected void initExtractorMap() {
            super.initExtractorMap();

            registerExtractor(ServletException.class, throwable -> {
                ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
                return ((ServletException)throwable).getRootCause();
            });
        }
    }
}
