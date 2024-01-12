package com.anthonylim.postalcodes.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PostalCodesException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private HttpStatus status;
    
    public PostalCodesException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
    
    public PostalCodesException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
