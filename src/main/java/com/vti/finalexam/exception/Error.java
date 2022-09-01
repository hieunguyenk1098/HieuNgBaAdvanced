package com.vti.finalexam.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private String errorCode;
    private String field;
    private HttpStatus status;
    private String message;

    public Error status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }

    public Error errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Error field(String field) {
        this.field = field;
        return this;
    }
}
