package dev.kovaliv.cloudflare.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareTranslateResponse extends CloudflareAbstractResponse {
    private Result result;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        @SerializedName("translated_text")
        private String translatedText;
    }
}
