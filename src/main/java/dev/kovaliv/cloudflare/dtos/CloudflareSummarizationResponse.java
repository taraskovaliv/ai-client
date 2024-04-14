package dev.kovaliv.cloudflare.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareSummarizationResponse extends CloudflareAbstractResponse {
    private Result result;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private String summary;
    }
}
