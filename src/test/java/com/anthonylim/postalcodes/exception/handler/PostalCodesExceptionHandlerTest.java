package com.anthonylim.postalcodes.exception.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.anthonylim.postalcodes.dto.ErrorResponse;
import com.anthonylim.postalcodes.exception.PostalCodesException;

public class PostalCodesExceptionHandlerTest {
    private PostalCodesExceptionHandler postalCodesExceptionHandler = new PostalCodesExceptionHandler();
    
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String message = "Internal server error";
    Throwable cause = new RuntimeException();
    PostalCodesException postalCodesException = new PostalCodesException(status, message, cause);
    
    @Test
    public void testHandlePostalCodesException() {
        ResponseEntity<ErrorResponse> response = postalCodesExceptionHandler.handlePostalCodesException(postalCodesException);
        Assertions.assertEquals(status, response.getStatusCode());
        Assertions.assertEquals(status.value(), response.getBody().getError().getCode());
        Assertions.assertEquals(message, response.getBody().getError().getMessage());
    }
}
