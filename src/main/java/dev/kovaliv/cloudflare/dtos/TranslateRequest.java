package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslateRequest extends AbstractRequest {
    private String text;
    @SerializedName("source_lang")
    private String sourceLang = "en";
    @SerializedName("target_lang")
    private String targetLang;

    public TranslateRequest(String text, String targetLang) {
        this.text = text;
        this.targetLang = targetLang;
    }
}
