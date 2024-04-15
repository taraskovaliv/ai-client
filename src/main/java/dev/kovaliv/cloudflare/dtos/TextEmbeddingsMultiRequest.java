package dev.kovaliv.cloudflare.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextEmbeddingsMultiRequest extends AbstractRequest {
    private List<String> text;
}
