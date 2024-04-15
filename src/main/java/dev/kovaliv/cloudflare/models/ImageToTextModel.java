package dev.kovaliv.cloudflare.models;

public class ImageToTextModel extends AbstractModel {
    public static final ImageToTextModel UFORM_GEN2_QWEN_500M = new ImageToTextModel("@cf/unum/uform-gen2-qwen-500m");

    public ImageToTextModel(String label) {
        super(label);
    }
}
