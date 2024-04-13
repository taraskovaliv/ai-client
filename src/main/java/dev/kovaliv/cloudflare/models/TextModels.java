package dev.kovaliv.cloudflare.models;

import lombok.Getter;

@Getter
public enum TextModels {
    LLAMA_2_7B_CHAT_FP16("@cf/meta/llama-2-7b-chat-fp16"),
    LLAMA_2_7B_CHAT_INT8("@cf/meta/llama-2-7b-chat-int8"),
    LLAMA_2_7B_CHAT_HF_LORA("@cf/meta-llama/llama-2-7b-chat-hf-lora"),
    LLAMA_2_13B_CHAT_AWQ("@hf/thebloke/llama-2-13b-chat-awq"),
    LLAMAGUARD_7B_AWQ("@hf/thebloke/llamaguard-7b-awq"),
    MISTRAL_7B_INSTRUCT_V0_1("@cf/mistral/mistral-7b-instruct-v0.1"),
    MISTRAL_7B_INSTRUCT_V0_2("@hf/mistral/mistral-7b-instruct-v0.2"),
    MISTRAL_7B_INSTRUCT_AWQ_V0_1("@hf/thebloke/mistral-7b-instruct-v0.1-awq"),
    MISTRAL_7B_INSTRUCT_LORA_V0_2("@cf/mistral/mistral-7b-instruct-v0.2-lora"),
    GEMMA_2B_IT_LORA("@cf/google/gemma-2b-it-lora"),
    GEMMA_7B_IT_LORA("@cf/google/gemma-7b-it-lora"),
    GEMMA_7B_IT("@hf/google/gemma-7b-it"),
    STARLING_LM_7B_BETA("@hf/nexusflow/starling-lm-7b-beta"),
    NEURAL_CHAT_7B_V3_1_AWQ("@hf/thebloke/neural-chat-7b-v3-1-awq"),
    TINYLLAMA_1_1B_CHAT("@cf/tinyllama/tinyllama-1.1b-chat-v1.0"),
    CODELLAMA_7B_INSTRUCT_AWQ("@hf/thebloke/codellama-7b-instruct-awq"),
    DISCOLM_GERMAN_7B_V1_AWQ("@cf/thebloke/discolm-german-7b-v1-awq"),
    OPENCHAT_3_5_AWQ("@hf/thebloke/openchat_3.5-awq"),
    OPENCHAT_3_5_0106("@cf/openchat/openchat-3.5-0106"),
    DEEPSEEK_MATH_7B_INSTRUCT("@cf/deepseek-ai/deepseek-math-7b-instruct"),
    DEEPSEEK_CODER_6_7B_BASE_AWQ("@hf/thebloke/deepseek-coder-6.7b-base-awq"),
    DEEPSEEK_CODER_6_7B_INSTRUCT_AWQ("@hf/thebloke/deepseek-coder-6.7b-instruct-awq"),
    OPENHERMES_2_5_MISTRAL_7B_AWQ("@hf/thebloke/openhermes-2.5-mistral-7b-awq"),
    FALCON_7B_INSTRUCT("@cf/tiiuae/falcon-7b-instruct"),
    HERMES_2_PRO_MISTRAL_7B("@hf/nousresearch/hermes-2-pro-mistral-7b"),
    ZEPHYR_7B_BETA_AWQ("@hf/thebloke/zephyr-7b-beta-awq"),
    SQLCODER_7B_2("@cf/defog/sqlcoder-7b-2"),
    PHI_2("@cf/microsoft/phi-2"),
    QWEN_1_5_0_5B_CHAT("@cf/qwen/qwen1.5-0.5b-chat"),
    QWEN_1_5_1_8B_CHAT("@cf/qwen/qwen1.5-1.8b-chat"),
    QWEN_1_5_7B_CHAT_AWQ("@cf/qwen/qwen1.5-7b-chat-awq"),
    QWEN_1_5_14B_CHAT_AWQ("@cf/qwen/qwen1.5-14b-chat-awq");

    private final String label;

    TextModels(String label) {
        this.label = label;
    }
}
