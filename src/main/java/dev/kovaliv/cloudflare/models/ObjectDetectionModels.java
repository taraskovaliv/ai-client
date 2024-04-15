package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum ObjectDetectionModels {
    DETR_RESNET_50("@cf/facebook/detr-resnet-50");

    private final String label;

    ObjectDetectionModels(String label) {
        this.label = label;
    }
}
