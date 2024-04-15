package dev.kovaliv.cloudflare.models;

public class TextGenerationModel extends AbstractModel {
    public static final TextGenerationModel LLAMA_2_7B_CHAT_FP16 = new TextGenerationModel("@cf/meta/llama-2-7b-chat-fp16");
    public static final TextGenerationModel LLAMA_2_7B_CHAT_INT8 = new TextGenerationModel("@cf/meta/llama-2-7b-chat-int8");
    public static final TextGenerationModel LLAMA_2_7B_CHAT_HF_LORA = new TextGenerationModel("@cf/meta-llama/llama-2-7b-chat-hf-lora");
    public static final TextGenerationModel LLAMA_2_13B_CHAT_AWQ = new TextGenerationModel("@hf/thebloke/llama-2-13b-chat-awq");
    public static final TextGenerationModel LLAMAGUARD_7B_AWQ = new TextGenerationModel("@hf/thebloke/llamaguard-7b-awq");
    public static final TextGenerationModel MISTRAL_7B_INSTRUCT_V0_1 = new TextGenerationModel("@cf/mistral/mistral-7b-instruct-v0.1");
    public static final TextGenerationModel MISTRAL_7B_INSTRUCT_V0_2 = new TextGenerationModel("@hf/mistral/mistral-7b-instruct-v0.2");
    public static final TextGenerationModel MISTRAL_7B_INSTRUCT_AWQ_V0_1 = new TextGenerationModel("@hf/thebloke/mistral-7b-instruct-v0.1-awq");
    public static final TextGenerationModel MISTRAL_7B_INSTRUCT_LORA_V0_2 = new TextGenerationModel("@cf/mistral/mistral-7b-instruct-v0.2-lora");
    public static final TextGenerationModel GEMMA_2B_IT_LORA = new TextGenerationModel("@cf/google/gemma-2b-it-lora");
    public static final TextGenerationModel GEMMA_7B_IT_LORA = new TextGenerationModel("@cf/google/gemma-7b-it-lora");
    public static final TextGenerationModel GEMMA_7B_IT = new TextGenerationModel("@hf/google/gemma-7b-it");
    public static final TextGenerationModel STARLING_LM_7B_BETA = new TextGenerationModel("@hf/nexusflow/starling-lm-7b-beta");
    public static final TextGenerationModel NEURAL_CHAT_7B_V3_1_AWQ = new TextGenerationModel("@hf/thebloke/neural-chat-7b-v3-1-awq");
    public static final TextGenerationModel TINYLLAMA_1_1B_CHAT = new TextGenerationModel("@cf/tinyllama/tinyllama-1.1b-chat-v1.0");
    public static final TextGenerationModel CODELLAMA_7B_INSTRUCT_AWQ = new TextGenerationModel("@hf/thebloke/codellama-7b-instruct-awq");
    public static final TextGenerationModel DISCOLM_GERMAN_7B_V1_AWQ = new TextGenerationModel("@cf/thebloke/discolm-german-7b-v1-awq");
    public static final TextGenerationModel OPENCHAT_3_5_AWQ = new TextGenerationModel("@hf/thebloke/openchat_3.5-awq");
    public static final TextGenerationModel OPENCHAT_3_5_0106 = new TextGenerationModel("@cf/openchat/openchat-3.5-0106");
    public static final TextGenerationModel DEEPSEEK_MATH_7B_INSTRUCT = new TextGenerationModel("@cf/deepseek-ai/deepseek-math-7b-instruct");
    public static final TextGenerationModel DEEPSEEK_CODER_6_7B_BASE_AWQ = new TextGenerationModel("@hf/thebloke/deepseek-coder-6.7b-base-awq");
    public static final TextGenerationModel DEEPSEEK_CODER_6_7B_INSTRUCT_AWQ = new TextGenerationModel("@hf/thebloke/deepseek-coder-6.7b-instruct-awq");
    public static final TextGenerationModel OPENHERMES_2_5_MISTRAL_7B_AWQ = new TextGenerationModel("@hf/thebloke/openhermes-2.5-mistral-7b-awq");
    public static final TextGenerationModel FALCON_7B_INSTRUCT = new TextGenerationModel("@cf/tiiuae/falcon-7b-instruct");
    public static final TextGenerationModel HERMES_2_PRO_MISTRAL_7B = new TextGenerationModel("@hf/nousresearch/hermes-2-pro-mistral-7b");
    public static final TextGenerationModel ZEPHYR_7B_BETA_AWQ = new TextGenerationModel("@hf/thebloke/zephyr-7b-beta-awq");
    public static final TextGenerationModel SQLCODER_7B_2 = new TextGenerationModel("@cf/defog/sqlcoder-7b-2");
    public static final TextGenerationModel PHI_2 = new TextGenerationModel("@cf/microsoft/phi-2");
    public static final TextGenerationModel QWEN_1_5_0_5B_CHAT = new TextGenerationModel("@cf/qwen/qwen1.5-0.5b-chat");
    public static final TextGenerationModel QWEN_1_5_1_8B_CHAT = new TextGenerationModel("@cf/qwen/qwen1.5-1.8b-chat");
    public static final TextGenerationModel QWEN_1_5_7B_CHAT_AWQ = new TextGenerationModel("@cf/qwen/qwen1.5-7b-chat-awq");
    public static final TextGenerationModel QWEN_1_5_14B_CHAT_AWQ = new TextGenerationModel("@cf/qwen/qwen1.5-14b-chat-awq");

    public TextGenerationModel(String label) {
        super(label);
    }
}
