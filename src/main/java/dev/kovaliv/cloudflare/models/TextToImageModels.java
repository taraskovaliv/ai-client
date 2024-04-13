package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum TextToImageModels {
    DREAMSHAPER_8_LCM("@cf/lykon/dreamshaper-8-lcm"),
    STABLE_DIFFUSION_XL_LIGHTNING("@cf/bytedance/stable-diffusion-xl-lightning"),
    STABLE_DIFFUSION_XL_BASE_1_0("@cf/stabilityai/stable-diffusion-xl-base-1.0"),
    STABLE_DIFFUSION_V1_5_INPAINTING("@cf/runwayml/stable-diffusion-v1-5-inpainting"),
    STABLE_DIFFUSION_V1_5_IMG2IMG("@cf/runwayml/stable-diffusion-v1-5-img2img");

    private final String label;

    TextToImageModels(String label) {
        this.label = label;
    }
}
