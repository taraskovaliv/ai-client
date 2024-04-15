package dev.kovaliv.cloudflare;

import com.google.gson.Gson;
import dev.kovaliv.cloudflare.dtos.*;
import dev.kovaliv.cloudflare.exception.CloudflareIllegalRequestException;
import dev.kovaliv.cloudflare.exception.CloudflareRequestException;
import dev.kovaliv.cloudflare.models.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;
import static org.apache.hc.core5.http.ContentType.APPLICATION_OCTET_STREAM;

public class CloudflareClient {
    private static final String cloudflareApiBaseUrl = "https://api.cloudflare.com/client/v4";
    private final String accountId;
    private final String authToken;

    public CloudflareClient(String accountId, String authToken) {
        this.accountId = accountId;
        this.authToken = authToken;
    }

    public TextGenerationResponse generate(TextGenerationRequest request, TextGenerationModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getJsonRequestBuilder(request, model).build();
        return getResponse(httpRequest, TextGenerationResponse.class);
    }

    public TextClassificationResponse generate(TextClassificationRequest request, TextClassificationModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getJsonRequestBuilder(request, model).build();
        return getResponse(httpRequest, TextClassificationResponse.class);
    }

    public File generate(TextToImageRequest request, TextToImageModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(request, model).build();
        try (CloseableHttpResponse response = executeRequest(httpRequest)) {
            return downloadImage(response.getEntity());
        } catch (IOException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    public TranslateResponse generate(TranslateRequest request, TranslationModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getJsonRequestBuilder(request, model).build();
        return getResponse(httpRequest, TranslateResponse.class);
    }

    public SummarizationResponse generate(SummarizationRequest request, SummarizationModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getJsonRequestBuilder(request, model).build();
        return getResponse(httpRequest, SummarizationResponse.class);
    }

    public TextEmbeddingsResponse generate(TextEmbeddingsRequest request, TextEmbeddingsModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getJsonRequestBuilder(request, model).build();
        return getResponse(httpRequest, TextEmbeddingsResponse.class);
    }

    public TextEmbeddingsResponse generate(TextEmbeddingsMultiRequest request, TextEmbeddingsModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getJsonRequestBuilder(request, model).build();
        return getResponse(httpRequest, TextEmbeddingsResponse.class);
    }

    public SpeechRecognitionResponse generate(File file, SpeechRecognitionModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(file, model).build();
        return getResponse(httpRequest, SpeechRecognitionResponse.class);
    }

    public ImageClassificationResponse generate(File file, ImageClassificationModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(file, model).build();
        return getResponse(httpRequest, ImageClassificationResponse.class);
    }

    public ImageToTextResponse generate(File file, ImageToTextModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(file, model).build();
        return getResponse(httpRequest, ImageToTextResponse.class);
    }

    public ObjectDetectionResponse generate(File file, ObjectDetectionModel model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(file, model).build();
        return getResponse(httpRequest, ObjectDetectionResponse.class);
    }

    private <T> T getResponse(ClassicHttpRequest httpRequest, Class<T> clazz) throws CloudflareRequestException {
        try (CloseableHttpResponse response = executeRequest(httpRequest)) {
            return parseResponse(response, clazz);
        } catch (IOException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    private <T> T parseResponse(ClassicHttpResponse response, Class<T> clazz) throws CloudflareRequestException {
        try {
            return new Gson().fromJson(EntityUtils.toString(response.getEntity(), UTF_8.name()), clazz);
        } catch (IOException | ParseException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    private ClassicRequestBuilder getBaseRequestBuilder(AbstractRequest request, AbstractModel model) {
        validateRequest(request);
        return ClassicRequestBuilder.post(getRequestUrl(model.getLabel()))
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", APPLICATION_JSON.getMimeType())
                .setEntity(new Gson().toJson(request), APPLICATION_JSON);
    }

    private ClassicRequestBuilder getBaseRequestBuilder(File file, AbstractModel model) {
        return ClassicRequestBuilder.post(getRequestUrl(model.getLabel()))
                .addHeader("Authorization", "Bearer " + authToken)
                .setEntity(new FileEntity(file, APPLICATION_OCTET_STREAM));
    }

    private ClassicRequestBuilder getJsonRequestBuilder(AbstractRequest request, AbstractModel model) {
        return getBaseRequestBuilder(request, model)
                .addHeader("Accept", APPLICATION_JSON.getMimeType());
    }

    private void validateRequest(AbstractRequest request) {
        if (request instanceof TextGenerationRequest) {
            TextGenerationRequest textGenerationRequest = (TextGenerationRequest) request;
            String prompt = textGenerationRequest.getPrompt();
            if (prompt == null || prompt.isBlank()) {
                throw new CloudflareIllegalRequestException("Prompt is required for Text Generation request");
            }
        } else if (request instanceof TextToImageRequest) {
            TextToImageRequest textToImageRequest = (TextToImageRequest) request;
            String prompt = textToImageRequest.getPrompt();
            if (prompt == null || prompt.isBlank()) {
                throw new CloudflareIllegalRequestException("Prompt is required for Text-to-Image request");
            }
        } else if (request instanceof TranslateRequest) {
            TranslateRequest translateRequest = (TranslateRequest) request;
            String text = translateRequest.getText();
            if (text == null || text.isBlank()) {
                throw new CloudflareIllegalRequestException("Text is required for Translation request");
            }
            String targetLang = translateRequest.getTargetLang();
            if (targetLang == null || targetLang.isBlank()) {
                throw new CloudflareIllegalRequestException("Target language is required for Translation request");
            }
        } else if (request instanceof SummarizationRequest) {
            SummarizationRequest summarizationRequest = (SummarizationRequest) request;
            String text = summarizationRequest.getInputText();
            if (text == null || text.isBlank()) {
                throw new CloudflareIllegalRequestException("Input Text is required for Summarization request");
            }
        } else if (request instanceof TextClassificationRequest) {
            TextClassificationRequest textClassificationRequest = (TextClassificationRequest) request;
            String text = textClassificationRequest.getText();
            if (text == null || text.isBlank()) {
                throw new CloudflareIllegalRequestException("Text is required for Text Classification request");
            }
        } else if (request instanceof TextEmbeddingsRequest) {
            TextEmbeddingsRequest textEmbeddingsRequest = (TextEmbeddingsRequest) request;
            String text = textEmbeddingsRequest.getText();
            if (text == null || text.isBlank()) {
                throw new CloudflareIllegalRequestException("Text is required for Text Embeddings request");
            }
        } else if (request instanceof TextEmbeddingsMultiRequest) {
            TextEmbeddingsMultiRequest textEmbeddingsMultiRequest = (TextEmbeddingsMultiRequest) request;
            List<String> texts = textEmbeddingsMultiRequest.getText();
            if (texts == null || texts.isEmpty()) {
                throw new CloudflareIllegalRequestException("Texts are required for Text Embeddings request");
            }
        }
    }

    private String getRequestUrl(String model) {
        return cloudflareApiBaseUrl + "/accounts/" + accountId + "/ai/run/" + model;
    }

    private CloseableHttpResponse executeRequest(ClassicHttpRequest httpRequest) throws CloudflareRequestException {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpRequest);
            if (response.getCode() < 300 && response.getCode() >= 200) {
                return response;
            } else {
                throw new CloudflareRequestException(parseResponse(response, ErrorResponse.class));
            }
        } catch (IOException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    private static File downloadImage(HttpEntity entity) throws IOException {
        BufferedImage image = ImageIO.read(entity.getContent());
        File theDir = new File("images/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        String tmpFilename = "images/" + getRandomNumber() + ".png";
        File outputfile = new File(tmpFilename);
        ImageIO.write(image, "png", outputfile);
        return outputfile;
    }

    private static Integer getRandomNumber() {
        return new Random().nextInt(8999999) + 1000000;
    }
}
