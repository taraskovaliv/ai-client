package dev.kovaliv.cloudflare.models;

public class SpeechRecognitionModel  extends AbstractModel {
    public static final SpeechRecognitionModel WHISPER = new SpeechRecognitionModel("@cf/openai/whisper");
    public static final SpeechRecognitionModel WHISPER_TINY_EN = new SpeechRecognitionModel("@cf/openai/whisper-tiny-en");

    public SpeechRecognitionModel(String label) {
        super(label);
    }
}
