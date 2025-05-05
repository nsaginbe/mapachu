package org.nurgisa.mapachu.exception;

import org.nurgisa.mapachu.util.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponse> handle(InvalidUserException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidPokemonException.class)
    public ResponseEntity<ErrorResponse> handle(InvalidPokemonException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
