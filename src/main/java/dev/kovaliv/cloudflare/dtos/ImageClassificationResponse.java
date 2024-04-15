package dev.kovaliv.cloudflare.dtos;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageClassificationResponse extends AbstractResponse {
    private List<Result> result;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private String label;
        private double score;
    }
}
