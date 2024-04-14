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

# Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Development](#contributing)

# Installation

Add dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>dev.kovaliv.cloudflare</groupId>
    <artifactId>ai-client</artifactId>
    <version>0.0.2</version>
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
CloudflareTextRequest request = new CloudflareTextRequest(prompt);
CloudflareTextResponse response = client.generate(request, TextModels.OPENCHAT_3_5_AWQ);
System.out.println(response.getResult().getResponse());
```

## Text-to-Image

```Java
String prompt = "Curly dog";
CloudflareImageRequest request = new CloudflareImageRequest(prompt);
File image = client.generate(request, TextToImageModels.STABLE_DIFFUSION_XL_LIGHTNING);
```

## Translation

```Java
String text = "Hello, how are you?";
CloudflareTranslationRequest request = new CloudflareTranslationRequest(text, "en", "es");
CloudflareTranslationResponse response = client.generate(request, TranslationModels.M2M_100_1_2B);
System.out.println(response.getResult().getTranslatedText());
```

## Summarization

```Java
String text = "Big text";
CloudflareSummarizationRequest request = new CloudflareSummarizationRequest(text);
CloudflareSummarizationResponse response = client.generate(request, SummarizationModels.BART_LARGE_CNN);
System.out.println(response.getResult().getSummary());
```

## Speech-to-Text

```Java
CloudflareSpeechRecognitionResponse response = client.generate(new File("audio.ogg"), SpeechRecognitionModels.WHISPER);
System.out.println(response.getResult().getText());
```

# Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/taraskovaliv/ai-client.

## License

This lib is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).