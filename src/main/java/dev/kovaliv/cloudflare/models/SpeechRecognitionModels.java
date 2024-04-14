package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum SpeechRecognitionModels {
    WHISPER("@cf/openai/whisper");

    private final String label;

    SpeechRecognitionModels(String label) {
        this.label = label;
    }
}
