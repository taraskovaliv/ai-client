package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextGenerationRequest extends AbstractRequest {
    @SerializedName("max_tokens")
    private int maxTokens = 256;
    private String prompt;
    private boolean raw = false;
    private boolean stream = false;

    public TextGenerationRequest(String prompt) {
        this.prompt = prompt;
    }
}
