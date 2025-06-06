package org.nurgisa.mapachu.util;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static String getErrorMessage(BindingResult bindingResult) {
        StringBuilder message = new StringBuilder();

        for (FieldError error : bindingResult.getFieldErrors()) {
            message.append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        return message.toString();
    }
}
