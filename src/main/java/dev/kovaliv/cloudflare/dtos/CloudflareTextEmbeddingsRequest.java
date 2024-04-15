package dev.kovaliv.cloudflare.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudflareTextEmbeddingsRequest extends CloudflareAbstractRequest {
    private String text;
}
