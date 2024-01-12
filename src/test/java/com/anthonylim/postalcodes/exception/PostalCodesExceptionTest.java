package com.anthonylim.postalcodes.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class PostalCodesExceptionTest {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String message = "Internal server error";
    Throwable cause = new RuntimeException();
    
    @Test
    public void testPostalCodesException() {
        PostalCodesException exception = new PostalCodesException(status, message, cause);
        Assertions.assertEquals(status, exception.getStatus());
        Assertions.assertEquals(message, exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
        
        PostalCodesException exceptionWithNoCause = new PostalCodesException(status, message);
        Assertions.assertEquals(status, exceptionWithNoCause.getStatus());
        Assertions.assertEquals(message, exceptionWithNoCause.getMessage());
    }
}
