package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareSummarizationRequest extends CloudflareAbstractRequest {
    @SerializedName("input_text")
    private String inputText;
    @SerializedName("max_length")
    private int maxLength = 1024;

    public CloudflareSummarizationRequest(String inputText) {
        this.inputText = inputText;
    }
}
