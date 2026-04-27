package dev.kovaliv.cloudflare.models;

public class TextToSpeechModel extends AbstractModel {
    public static final TextToSpeechModel AURA_2_EN = new TextToSpeechModel("@cf/deepgram/aura-2-en");
    public static final TextToSpeechModel AURA_2_ES = new TextToSpeechModel("@cf/deepgram/aura-2-es");
    public static final TextToSpeechModel MELOTTS = new TextToSpeechModel("@cf/myshell-ai/melotts");

    public TextToSpeechModel(String label) {
        super(label);
    }
}
