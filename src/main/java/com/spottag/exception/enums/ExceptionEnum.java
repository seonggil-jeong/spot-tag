package com.spottag.exception.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "001", "Access Denied"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "002", "Bad Request"),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "003", "Runtime exception"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "004", "Internal server error")
    ;

    private final HttpStatus status;
    private final String result;
    private final String message;

    ExceptionEnum(HttpStatus status, String result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

}
