package com.vti.finalexam.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vti.finalexam.utils.HttpUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final HttpUtils httpUtils;

    public AuthExceptionHandler(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        populateAuthError(request, response, "authentication.failureLogin");
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
        populateAuthError(request, response, "authorization.accessDenied");
    }

    private void populateAuthError(HttpServletRequest request, HttpServletResponse response,
        String errorCode)
        throws IOException {
        Map<String, String> headers = Collections.list(request.getHeaderNames())
            .stream()
            .collect(Collectors.toMap(h -> h, request::getHeader));

        ICommonException commonException = new BusinessErrorException()
            .businessError(
                new BusinessError()
                    .errorCode(errorCode)
            );

        Error error = new Error()
            .errorCode(errorCode)
            .status(
                errorCode.equals("authentication.failureLogin") ?
                    HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN)
            .message(
                Objects.requireNonNull(
                    httpUtils.populateMessage(
                        commonException,
                        new Locale(httpUtils.getLanguage(headers))))
                    .getErrorMessage());

        // object -> json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(error);

        // return json
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}
