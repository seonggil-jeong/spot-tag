package com.spottag.exception;

import com.spottag.exception.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionEnum exception;

    public CustomException(ExceptionEnum ex) {
        super(ex.getMessage());
        this.exception = ex;
    }
}
