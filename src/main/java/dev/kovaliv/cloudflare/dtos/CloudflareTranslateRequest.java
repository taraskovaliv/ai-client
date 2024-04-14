package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareTranslateRequest extends CloudflareAbstractRequest {
    private String text;
    @SerializedName("source_lang")
    private String sourceLang = "en";
    @SerializedName("target_lang")
    private String targetLang;

    public CloudflareTranslateRequest(String text, String targetLang) {
        this.text = text;
        this.targetLang = targetLang;
    }
}
