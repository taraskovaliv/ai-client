package dev.kovaliv.cloudflare.models;

public class ImageToTextModel extends AbstractModel {
    public static final ImageToTextModel LLAVA_HF_LLAVA_1_5_7B_HF = new ImageToTextModel("@cf/llava-hf/llava-1.5-7b-hf");
    public static final ImageToTextModel UFORM_GEN2_QWEN_500M = new ImageToTextModel("@cf/unum/uform-gen2-qwen-500m");

    public ImageToTextModel(String label) {
        super(label);
    }
}
