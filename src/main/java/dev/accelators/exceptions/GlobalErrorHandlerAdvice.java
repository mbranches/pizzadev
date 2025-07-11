package dev.accelators.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultMessageError> handlerNotFoundException(NotFoundException e) {
        DefaultMessageError response = new DefaultMessageError(HttpStatus.NOT_FOUND.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DefaultMessageError> handlerBadRequestException(BadRequestException e) {
        DefaultMessageError response = new DefaultMessageError(HttpStatus.BAD_REQUEST.value(), e.getReason());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultMessageError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.joining(", "));

        DefaultMessageError errorResponse = new DefaultMessageError(HttpStatus.BAD_REQUEST.value(), defaultMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DefaultMessageError> handlerAuthenticationException(AuthenticationException e) {
        DefaultMessageError response = new DefaultMessageError(HttpStatus.UNAUTHORIZED.value(), "Email or password invalid");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
