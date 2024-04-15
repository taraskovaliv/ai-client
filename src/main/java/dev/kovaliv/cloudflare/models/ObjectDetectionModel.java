package dev.kovaliv.cloudflare.models;

public class ObjectDetectionModel extends AbstractModel {
    public static final ObjectDetectionModel DETR_RESNET_50 = new ObjectDetectionModel("@cf/facebook/detr-resnet-50");

    public ObjectDetectionModel(String label) {
        super(label);
    }
}
