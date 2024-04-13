package dev.kovaliv.cloudflare.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareTextRequest {
    private int max_tokens = 256;
    private String prompt;
    private boolean raw = false;
    private boolean stream = false;

    public CloudflareTextRequest(String prompt) {
        this.prompt = prompt;
    }
}
