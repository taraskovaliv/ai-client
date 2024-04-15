package dev.kovaliv.cloudflare.exception;

import dev.kovaliv.cloudflare.dtos.ErrorResponse;

import java.util.stream.Collectors;

public class CloudflareRequestException extends Exception {
    public CloudflareRequestException(String message) {
        super(message);
    }

    public CloudflareRequestException(ErrorResponse errorResponse) {
        super(getErrorMessage(errorResponse));
    }

    public CloudflareRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    private static String getErrorMessage(ErrorResponse errorResponse) {
        String message = "Failed to execute request.";
        if (errorResponse != null && errorResponse.getErrors() != null && !errorResponse.getErrors().isEmpty()) {
            message += " Errors:\n";
            message += errorResponse.getErrors().stream()
                    .map(e -> String.format("Code: %d, Message: %s", e.getCode(), e.getMessage()))
                    .collect(Collectors.joining("\n"));
        }
        return message;
    }
}
