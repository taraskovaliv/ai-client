package dev.kovaliv.cloudflare.models;

public class SummarizationModel extends AbstractModel {
    public static final SummarizationModel BART_LARGE_CNN = new SummarizationModel("@cf/facebook/bart-large-cnn");

    public SummarizationModel(String label) {
        super(label);
    }
}
