package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareImageRequest extends CloudflareAbstractRequest {
    private String prompt;
    @SerializedName("num_steps")
    private int numSteps = 20;
    private double strength = 1.0;
    private double guidance = 7.5;

    public CloudflareImageRequest(String prompt) {
        this.prompt = prompt;
    }
}
