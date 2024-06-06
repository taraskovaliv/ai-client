Cloudflare Workers AI API client for Java
---
Cloudflare is testing its [Workers AI API](https://developers.cloudflare.com/workers-ai/get-started/rest-api/).
Hopefully this project makes it easier for java developers 
to consume Cloudflare's latest and greatest.

![Maven Central Version](https://img.shields.io/maven-central/v/dev.kovaliv.cloudflare/ai-client)
![GitHub License](https://img.shields.io/github/license/taraskovaliv/ai-client)

# Supported features
* [x] [Text Generation](https://developers.cloudflare.com/workers-ai/models/#text-generation)
* [x] [Text-to-Image](https://developers.cloudflare.com/workers-ai/models/#text-to-image)
* [x] [Translation](https://developers.cloudflare.com/workers-ai/models/#translation)
* [x] [Summarization](https://developers.cloudflare.com/workers-ai/models/#summarization)
* [x] [Speech-to-Text](https://developers.cloudflare.com/workers-ai/models/#automatic-speech-recognition)
* [x] [Image Classification](https://developers.cloudflare.com/workers-ai/models/#image-classification)
* [x] [Image-to-Text](https://developers.cloudflare.com/workers-ai/models/#image-to-text)
* [x] [Object Detection](https://developers.cloudflare.com/workers-ai/models/#object-detection)
* [x] [Text Embeddings](https://developers.cloudflare.com/workers-ai/models/#text-embeddings)
* [x] [Text Classification](https://developers.cloudflare.com/workers-ai/models/#text-classification)

# Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

# Installation

Add dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>dev.kovaliv.cloudflare</groupId>
    <artifactId>ai-client</artifactId>
    <version>0.1.1</version>
</dependency>
```

# Usage

## Cloudflare Workers AI
Please visit the [Cloudflare Workers AI website](https://developers.cloudflare.com/workers-ai/) for more details.
This lib provides a client that wraps around [Cloudflare's REST API](https://developers.cloudflare.com/workers-ai/get-started/rest-api/).

## Client

```Java
CloudflareClient client = new CloudflareClient(getenv("CLOUDFLARE_ACCOUNT_ID"), getenv("CLOUDFLARE_AUTH_TOKEN"));
```

## Text Generation

```Java
String prompt = "Hello, how are you?";
TextGenerationRequest request = new TextGenerationRequest(prompt);
TextGenerationResponse response = client.generate(request, TextModel.OPENCHAT_3_5_AWQ);
System.out.println(response.getResult().getResponse());
```

## Text-to-Image

```Java
String prompt = "Curly dog";
ImageRequest request = new ImageRequest(prompt);
File image = client.generate(request, TextToImageModel.STABLE_DIFFUSION_XL_LIGHTNING);
```

## Translation

```Java
String text = "Hello, how are you?";
TranslationRequest request = new TranslationRequest(text, "en", "es");
TranslationResponse response = client.generate(request, TranslationModel.M2M_100_1_2B);
System.out.println(response.getResult().getTranslatedText());
```

## Summarization

```Java
String text = "Big text";
SummarizationRequest request = new SummarizationRequest(text);
SummarizationResponse response = client.generate(request, SummarizationModel.BART_LARGE_CNN);
System.out.println(response.getResult().getSummary());
```

## Speech-to-Text

```Java
SpeechRecognitionResponse response = client.generate(new File("audio.ogg"), SpeechRecognitionModel.WHISPER);
System.out.println(response.getResult().getText());
```

## Image Classification

```Java
ImageClassificationResponse response = client.generate(new File("image.jpg"), ImageClassificationModel.RESNET_50);
```

## Image-to-Text

```Java
ImageToTextResponse response = client.generate(new File("image.jpg"), ImageToTextModel.UFORM_GEN2_QWEN_500M);
System.out.println(response.getResult().getDescription());
```

## Object Detection

```Java
ObjectDetectionResponse response = client.generate(new File("image.jpg"), ObjectDetectionModel.DETR_RESNET_50);
```

## Text Embedding

With a single text:

```Java
String text = "Hello, how are you?";
TextEmbeddingsRequest request = new TextEmbeddingsRequest(text);
TextEmbeddingsResponse response = client.generate(request, TextEmbeddingsModel.BG_BASE_EN_V1_5);
```

With multiple texts:

```Java
List<String> text = List.of("Hello, how are you?", "I am fine, thank you.", "Goodbye!");
TextEmbeddingsMultiRequest request = new TextEmbeddingsMultiRequest(text);
TextEmbeddingsResponse response = client.generate(request, TextEmbeddingsModel.BG_LARGE_EN_V1_5);
```

## Text Classification

```Java
String text = "Hello, how are you?";
TextClassificationRequest request = new TextClassificationRequest(text);
TextClassificationResponse response = client.generate(request, TextClassificationModel.DISTILBERT_SST_2_INT8);
```

# Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/taraskovaliv/ai-client.

## License

This lib is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
