package dev.kovaliv.cloudflare.exception;

public class CloudflareIllegalRequestException extends IllegalArgumentException {
    public CloudflareIllegalRequestException(String message) {
        super(message);
    }
}
