package com.openbook.openbook.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OpenBookException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public OpenBookException(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
