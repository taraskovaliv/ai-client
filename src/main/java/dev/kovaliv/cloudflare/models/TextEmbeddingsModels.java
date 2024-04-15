package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum TextEmbeddingsModels {
    BG_BASE_EN_V1_5("@cf/baai/bge-base-en-v1.5"),
    BG_LARGE_EN_V1_5("@cf/baai/bge-large-en-v1.5"),
    BG_SMALL_EN_V1_5("@cf/baai/bge-small-en-v1.5");

    private final String label;

    TextEmbeddingsModels(String label) {
        this.label = label;
    }
}
