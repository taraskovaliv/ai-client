package dev.kovaliv.cloudflare.models;

public class TranslationModel extends AbstractModel {
    public static final TranslationModel M2M_100_1_2B = new TranslationModel("@cf/meta/m2m100-1.2b");

    public TranslationModel(String label) {
        super(label);
    }
}
