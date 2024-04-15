package dev.kovaliv.cloudflare.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextEmbeddingsResponse extends AbstractResponse {
    private Result result;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private List<Double> shape;
        private List<List<Double>> data;
    }
}
