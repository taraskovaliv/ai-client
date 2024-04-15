package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum ImageClassificationModels {
    RESNET_50("@cf/microsoft/resnet-50");

    private final String label;

    ImageClassificationModels(String label) {
        this.label = label;
    }
}
