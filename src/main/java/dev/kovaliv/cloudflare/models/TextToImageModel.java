package dev.kovaliv.cloudflare.models;

public class TextToImageModel extends AbstractModel {
    public static final TextToImageModel DREAMSHAPER_8_LCM = new TextToImageModel("@cf/lykon/dreamshaper-8-lcm");
    public static final TextToImageModel FLUX_1_SCHNELL = new TextToImageModel("@cf/black-forest-labs/flux-1-schnell");
    public static final TextToImageModel FLUX_2_KLEIN_9B = new TextToImageModel("@cf/black-forest-labs/flux-2-klein-9b");
    public static final TextToImageModel FLUX_2_DEV = new TextToImageModel("@cf/black-forest-labs/flux-2-dev");
    public static final TextToImageModel STABLE_DIFFUSION_XL_LIGHTNING = new TextToImageModel("@cf/bytedance/stable-diffusion-xl-lightning");
    public static final TextToImageModel STABLE_DIFFUSION_XL_BASE_1_0 = new TextToImageModel("@cf/stabilityai/stable-diffusion-xl-base-1.0");
    public static final TextToImageModel STABLE_DIFFUSION_XL_TURBO = new TextToImageModel("@cf/stabilityai/stable-diffusion-xl-turbo");
    public static final TextToImageModel STABLE_DIFFUSION_V1_5_INPAINTING = new TextToImageModel("@cf/runwayml/stable-diffusion-v1-5-inpainting");
    public static final TextToImageModel STABLE_DIFFUSION_V1_5_IMG2IMG = new TextToImageModel("@cf/runwayml/stable-diffusion-v1-5-img2img");

    public TextToImageModel(String label) {
        super(label);
    }
}
