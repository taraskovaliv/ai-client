package dev.kovaliv.cloudflare.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractResponse {
    protected boolean success;
    protected List<Error> errors;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private int code;
        private String message;
    }
}
