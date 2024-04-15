package dev.kovaliv.cloudflare.models;

public class TextEmbeddingsModel extends AbstractModel {
    public static final TextEmbeddingsModel BG_BASE_EN_V1_5 = new TextEmbeddingsModel("@cf/baai/bge-base-en-v1.5");
    public static final TextEmbeddingsModel BG_LARGE_EN_V1_5 = new TextEmbeddingsModel("@cf/baai/bge-large-en-v1.5");
    public static final TextEmbeddingsModel BG_SMALL_EN_V1_5 = new TextEmbeddingsModel("@cf/baai/bge-small-en-v1.5");

    public TextEmbeddingsModel(String label) {
        super(label);
    }
}
