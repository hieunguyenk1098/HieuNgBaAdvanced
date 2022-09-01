package com.vti.finalexam.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BusinessErrorException extends RuntimeException implements ICommonException {

    private static final long serialVersionUID = 1L;

    private BusinessError businessError;

    public BusinessErrorException businessError(BusinessError businessError) {
        this.businessError = businessError;
        return this;
    }

    @Override
    public BusinessError getBusinessError() {
        return businessError;
    }
}
