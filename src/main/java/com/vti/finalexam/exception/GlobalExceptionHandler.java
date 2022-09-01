package com.vti.finalexam.exception;

import com.vti.finalexam.utils.HttpUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpUtils httpUtils;

    public GlobalExceptionHandler(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
        HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        List<Error> errorList = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            String errorCode = error.getDefaultMessage();
            ICommonException commonException = new BusinessErrorException()
                .businessError(
                    new BusinessError()
                        .errorCode(errorCode)
                        .params(Collections.singletonList(field)));

            errorList.add(
                new Error()
                    .errorCode(errorCode)
                    .field(field)
                    .status(HttpStatus.BAD_REQUEST)
                    .message(
                        Objects.requireNonNull(
                            httpUtils.populateMessage(
                                commonException,
                                new Locale(httpUtils.getLanguage(webRequest))))
                            .getErrorMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(BusinessErrorException.class)
    public ResponseEntity<Object> handleBusinessErrorException(BusinessErrorException ex,
        WebRequest webRequest) {
        Error error = new Error()
            .errorCode(ex.getBusinessError().getErrorCode())
            .message(
                Objects.requireNonNull(
                    httpUtils.populateMessage(ex, new Locale(
                        Objects.requireNonNull(httpUtils.getLanguage(webRequest))))).getErrorMessage()
            )
            .status(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}