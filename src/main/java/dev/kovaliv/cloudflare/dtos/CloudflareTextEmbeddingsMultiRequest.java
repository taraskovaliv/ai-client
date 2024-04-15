package dev.kovaliv.cloudflare.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareTextEmbeddingsMultiRequest extends CloudflareAbstractRequest {
    private List<String> text;
}
