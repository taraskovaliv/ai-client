package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareTextRequest extends CloudflareAbstractRequest {
    @SerializedName("max_tokens")
    private int maxTokens = 256;
    private String prompt;
    private boolean raw = false;
    private boolean stream = false;

    public CloudflareTextRequest(String prompt) {
        this.prompt = prompt;
    }
}
