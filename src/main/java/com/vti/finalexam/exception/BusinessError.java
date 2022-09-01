package com.vti.finalexam.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessError {
    private String errorCode;

    private String errorMessage;

    private Object params;

    public BusinessError errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public BusinessError errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public BusinessError params(Object params) {
        this.params = params;
        return this;
    }
}
