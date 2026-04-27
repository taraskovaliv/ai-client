package dev.kovaliv.cloudflare.models;

public class TextClassificationModel extends AbstractModel {
    public static final TextClassificationModel DISTILBERT_SST_2_INT8 = new TextClassificationModel("@cf/huggingface/distilbert-sst-2-int8");
    public static final TextClassificationModel ROBERTA_SPAM = new TextClassificationModel("@cf/jpmorganchase/roberta-spam");
    public static final TextClassificationModel INML_ROBERTA_DGA = new TextClassificationModel("@cf/inml/inml-roberta-dga");

    public TextClassificationModel(String label) {
        super(label);
    }
}
