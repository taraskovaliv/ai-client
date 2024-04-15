package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum ImageToTextModels {
    UFORM_GEN2_QWEN_500M("@cf/unum/uform-gen2-qwen-500m");

    private final String label;

    ImageToTextModels(String label) {
        this.label = label;
    }
}
