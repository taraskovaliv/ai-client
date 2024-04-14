package dev.kovaliv.cloudflare.exception;

public class CloudflareRequestException extends Exception {
    public CloudflareRequestException(String message) {
        super(message);
    }

    public CloudflareRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
