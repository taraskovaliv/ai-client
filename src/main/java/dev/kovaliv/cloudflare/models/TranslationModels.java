package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum TranslationModels {
    M2M_100_1_2B("@cf/meta/m2m100-1.2b");

    private final String label;

    TranslationModels(String label) {
        this.label = label;
    }
}
