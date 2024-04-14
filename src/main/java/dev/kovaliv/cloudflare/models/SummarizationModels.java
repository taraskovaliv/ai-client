package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum SummarizationModels {
    BART_LARGE_CNN("@cf/facebook/bart-large-cnn");

    private final String label;

    SummarizationModels(String label) {
        this.label = label;
    }
}
