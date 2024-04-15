package dev.kovaliv.cloudflare.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDetectionResponse extends AbstractResponse {
    private List<Result> result;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private String label;
        private double score;
        private Box box;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Box {
        private double xmin;
        private double ymin;
        private double xmax;
        private double ymax;
    }
}
