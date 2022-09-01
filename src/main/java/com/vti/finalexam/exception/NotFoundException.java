package com.vti.finalexam.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String field;

    private String message;

    public NotFoundException(String errorCode, String field, String message) {
        this.errorCode = errorCode;
        this.field = field;
        this.message = message;
    }

    public NotFoundException(String message, String errorCode, String field, String message1) {
        super(message);
        this.errorCode = errorCode;
        this.field = field;
        this.message = message1;
    }

    public NotFoundException(String message, Throwable cause, String errorCode, String field,
        String message1) {
        super(message, cause);
        this.errorCode = errorCode;
        this.field = field;
        this.message = message1;
    }

    public NotFoundException(Throwable cause, String errorCode, String field, String message) {
        super(cause);
        this.errorCode = errorCode;
        this.field = field;
        this.message = message;
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, String errorCode, String field, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.field = field;
        this.message = message1;
    }
}
