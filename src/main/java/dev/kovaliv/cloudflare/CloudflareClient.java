package dev.kovaliv.cloudflare;

import com.google.gson.Gson;
import dev.kovaliv.cloudflare.dtos.*;
import dev.kovaliv.cloudflare.exception.CloudflareIllegalRequestException;
import dev.kovaliv.cloudflare.exception.CloudflareRequestException;
import dev.kovaliv.cloudflare.models.SummarizationModels;
import dev.kovaliv.cloudflare.models.TextModels;
import dev.kovaliv.cloudflare.models.TextToImageModels;
import dev.kovaliv.cloudflare.models.TranslationModels;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

public class CloudflareClient {
    private static final String cloudflareApiBaseUrl = "https://api.cloudflare.com/client/v4";
    private final String accountId;
    private final String authToken;

    public CloudflareClient(String accountId, String authToken) {
        this.accountId = accountId;
        this.authToken = authToken;
    }

    public CloudflareTextResponse generate(CloudflareTextRequest request, TextModels model) throws CloudflareRequestException {
        return generate(request, model.getLabel());
    }

    public File generate(CloudflareImageRequest request, TextToImageModels model) throws CloudflareRequestException {
        return generate(request, model.getLabel());
    }

    public CloudflareTranslateResponse generate(CloudflareTranslateRequest request, TranslationModels model) throws CloudflareRequestException {
        return generate(request, model.getLabel());
    }

    public CloudflareSummarizationResponse generate(CloudflareSummarizationRequest request, SummarizationModels model) throws CloudflareRequestException {
        return generate(request, model.getLabel());
    }

    public CloudflareTextResponse generate(CloudflareTextRequest request, String model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(request, model)
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON))
                .build();

        try (CloseableHttpResponse response = executeRequest(httpRequest)) {
            return parseResponse(response, CloudflareTextResponse.class);
        } catch (IOException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    public File generate(CloudflareImageRequest request, String model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(request, model).build();

        try (CloseableHttpResponse response = executeRequest(httpRequest)) {
            return downloadImage(response.getEntity());
        } catch (IOException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    public CloudflareTranslateResponse generate(CloudflareTranslateRequest request, String model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(request, model)
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON))
                .build();

        try (CloseableHttpResponse response = executeRequest(httpRequest)) {
            return parseResponse(response, CloudflareTranslateResponse.class);
        } catch (IOException e) {
            throw new CloudflareRequestException(e.getMessage(), e);
        }
    }

    public CloudflareSummarizationResponse generate(CloudflareSummarizationRequest request, String model) throws CloudflareRequestException {
        ClassicHttpRequest httpRequest = getBaseRequestBuilder(request, model)
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON))
                .build();

        try (CloseableHttpResponse response = executeRequest(httpRequest)) {
            return parseResponse(response, CloudflareSummarizationResponse.class);
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

    private ClassicRequestBuilder getBaseRequestBuilder(CloudflareAbstractRequest request, String model) {
        validateRequest(request);
        return ClassicRequestBuilder.post(getRequestUrl(model))
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", APPLICATION_JSON.getMimeType())
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON));
    }

    private void validateRequest(CloudflareAbstractRequest request) {
        if (request instanceof CloudflareTextRequest textRequest) {
            String prompt = textRequest.getPrompt();
            if (prompt == null || prompt.isBlank()) {
                throw new CloudflareIllegalRequestException("Prompt is required for Text Generation request");
            }
        } else if (request instanceof CloudflareImageRequest imageRequest) {
            String prompt = imageRequest.getPrompt();
            if (prompt == null || prompt.isBlank()) {
                throw new CloudflareIllegalRequestException("Prompt is required for Text-to-Image request");
            }
        } else if (request instanceof CloudflareTranslateRequest translateRequest) {
            String text = translateRequest.getText();
            if (text == null || text.isBlank()) {
                throw new CloudflareIllegalRequestException("Text is required for Translation request");
            }
            String targetLang = translateRequest.getTargetLang();
            if (targetLang == null || targetLang.isBlank()) {
                throw new CloudflareIllegalRequestException("Target language is required for Translation request");
            }
        } else if (request instanceof CloudflareSummarizationRequest summarizationRequest) {
            String text = summarizationRequest.getInputText();
            if (text == null || text.isBlank()) {
                throw new CloudflareIllegalRequestException("Input Text is required for Summarization request");
            }
        }
    }

    private String getRequestUrl(String model) {
        return cloudflareApiBaseUrl + "/accounts/" + accountId + "/ai/run/" + model;
    }

    private CloseableHttpResponse executeRequest(ClassicHttpRequest httpRequest) throws CloudflareRequestException {
        for (int retryCount = 0; retryCount < 5; retryCount++) {
            try {
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(httpRequest);
                if (response.getCode() != 500) {
                    return response;
                }
            } catch (IOException e) {
                throw new CloudflareRequestException(e.getMessage(), e);
            }
        }
        throw new CloudflareRequestException("Failed to get response from Cloudflare");
    }

    private File downloadImage(HttpEntity entity) throws IOException {
        BufferedImage image = ImageIO.read(entity.getContent());
        File theDir = new File("images/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        String tmpFilename = "images/" + new Random().nextInt(1000000, 10000000) + ".png";
        File outputfile = new File(tmpFilename);
        ImageIO.write(image, "png", outputfile);
        return outputfile;
    }
}
