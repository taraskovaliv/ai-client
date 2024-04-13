package dev.kovaliv.cloudflare.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareImageRequest {
    private String prompt;
    private int num_steps = 20;
    private double strength = 1.0;
    private double guidance = 7.5;

    public CloudflareImageRequest(String prompt) {
        this.prompt = prompt;
    }
}
