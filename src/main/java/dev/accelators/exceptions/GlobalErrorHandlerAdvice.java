package dev.accelators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultMessageError> handlerNotFoundException(NotFoundException e) {
        DefaultMessageError response = new DefaultMessageError(HttpStatus.UNAUTHORIZED.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
