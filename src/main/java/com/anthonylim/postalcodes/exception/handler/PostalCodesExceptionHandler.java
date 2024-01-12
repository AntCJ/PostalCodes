package com.anthonylim.postalcodes.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.anthonylim.postalcodes.dto.ErrorDTO;
import com.anthonylim.postalcodes.dto.ErrorResponse;
import com.anthonylim.postalcodes.exception.PostalCodesException;

@ControllerAdvice
public class PostalCodesExceptionHandler {
    @ExceptionHandler({ PostalCodesException.class })
    public ResponseEntity<ErrorResponse> handlePostalCodesException(PostalCodesException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorResponse(new ErrorDTO(exception.getStatus().value(), exception.getMessage())));
    }
}
