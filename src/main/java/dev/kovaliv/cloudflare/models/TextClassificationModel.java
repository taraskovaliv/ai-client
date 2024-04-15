package dev.kovaliv.cloudflare.models;

public class TextClassificationModel extends AbstractModel {
    public static final TextClassificationModel DISTILBERT_SST_2_INT8 = new TextClassificationModel("@cf/huggingface/distilbert-sst-2-int8");

    public TextClassificationModel(String label) {
        super(label);
    }
}
