package dev.kovaliv.cloudflare.models;

public class SpeechRecognitionModel  extends AbstractModel {
    public static final SpeechRecognitionModel WHISPER = new SpeechRecognitionModel("@cf/openai/whisper");

    public SpeechRecognitionModel(String label) {
        super(label);
    }
}
