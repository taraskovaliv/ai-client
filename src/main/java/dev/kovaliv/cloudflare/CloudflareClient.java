package dev.kovaliv.cloudflare;

import com.google.gson.Gson;
import dev.kovaliv.cloudflare.dtos.*;
import dev.kovaliv.cloudflare.models.TextModels;
import dev.kovaliv.cloudflare.models.TextToImageModels;
import dev.kovaliv.cloudflare.models.TranslationModels;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public CloudflareTextResponse generate(CloudflareTextRequest request, TextModels model) {
        return generate(request, model.getLabel());
    }

    public File generate(CloudflareImageRequest request, TextToImageModels model) {
        return generate(request, model.getLabel());
    }

    public CloudflareTranslateResponse generate(CloudflareTranslateRequest request, TranslationModels model) {
        return generate(request, model.getLabel());
    }

    public CloudflareTextResponse generate(CloudflareTextRequest request, String model) {
        boolean retry = true;
        String requestUrl = cloudflareApiBaseUrl + "/accounts/" + accountId + "/ai/run/" + model;
        ClassicHttpRequest httpRequest = ClassicRequestBuilder.post(requestUrl)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept", APPLICATION_JSON.getMimeType())
                .addHeader("Content-Type", APPLICATION_JSON.getMimeType())
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON))
                .build();

        CloseableHttpResponse response;
        String responseString = "";
        while (retry) {
            retry = false;
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                response = httpClient.execute(httpRequest);
                responseString = EntityUtils.toString(response.getEntity(), UTF_8.name());
            } catch (HttpResponseException e) {
                retry = true;
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().fromJson(responseString, CloudflareTextResponse.class);
    }

    public File generate(CloudflareImageRequest request, String model) {
        String requestUrl = cloudflareApiBaseUrl + "/accounts/" + accountId + "/ai/run/" + model;
        ClassicHttpRequest httpRequest = ClassicRequestBuilder.post(requestUrl)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", APPLICATION_JSON.getMimeType())
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON))
                .build();

        CloseableHttpResponse response;

        File responseImage = null;
        boolean retry = true;
        for (int retryCount = 0; retry && retryCount < 5; retryCount++) {
            retry = false;
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                response = httpClient.execute(httpRequest);
                if (response.getCode() == 500) {
                    retry = true;
                } else {
                    responseImage = downloadImage(response.getEntity().getContent());
                }
            } catch (HttpResponseException e) {
                retry = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (responseImage == null) {
            throw new RuntimeException("Failed to get response from Cloudflare");
        }
        return responseImage;
    }

    public CloudflareTranslateResponse generate(CloudflareTranslateRequest request, String model) {
        boolean retry = true;
        String requestUrl = cloudflareApiBaseUrl + "/accounts/" + accountId + "/ai/run/" + model;
        ClassicHttpRequest httpRequest = ClassicRequestBuilder.post(requestUrl)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Accept", APPLICATION_JSON.getMimeType())
                .addHeader("Content-Type", APPLICATION_JSON.getMimeType())
                .setEntity(new StringEntity(new Gson().toJson(request), APPLICATION_JSON))
                .build();

        CloseableHttpResponse response;
        String responseString = "";
        while (retry) {
            retry = false;
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                response = httpClient.execute(httpRequest);
                responseString = EntityUtils.toString(response.getEntity(), UTF_8.name());
            } catch (HttpResponseException e) {
                retry = true;
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().fromJson(responseString, CloudflareTranslateResponse.class);
    }

    private File downloadImage(InputStream stream) throws IOException {
        BufferedImage image = ImageIO.read(stream);
        File theDir = new File("images/");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        String tmpFilename = "images/" + new Random().nextInt(1000000, 10000000) + ".png";
        File outputfile = new File(tmpFilename);
        ImageIO.write(image, "png", outputfile);
        return outputfile;
    }
}
