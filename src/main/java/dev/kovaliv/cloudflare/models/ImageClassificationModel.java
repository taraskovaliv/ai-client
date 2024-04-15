package dev.kovaliv.cloudflare.models;

public class ImageClassificationModel extends AbstractModel {
    public static final ImageClassificationModel RESNET_50 = new ImageClassificationModel("@cf/microsoft/resnet-50");

    public ImageClassificationModel(String label) {
        super(label);
    }
}
