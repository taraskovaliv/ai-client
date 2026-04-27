package dev.kovaliv.cloudflare.models;

public class SpeechRecognitionModel  extends AbstractModel {
    public static final SpeechRecognitionModel WHISPER = new SpeechRecognitionModel("@cf/openai/whisper");
    public static final SpeechRecognitionModel WHISPER_TINY_EN = new SpeechRecognitionModel("@cf/openai/whisper-tiny-en");
    public static final SpeechRecognitionModel WHISPER_SHERPA = new SpeechRecognitionModel("@cf/openai/whisper-sherpa");
    public static final SpeechRecognitionModel WHISPER_LARGE_V3_TURBO = new SpeechRecognitionModel("@cf/openai/whisper-large-v3-turbo");
    public static final SpeechRecognitionModel DEEPGRAM_NOVA_3 = new SpeechRecognitionModel("@cf/deepgram/nova-3");

    public SpeechRecognitionModel(String label) {
        super(label);
    }
}
