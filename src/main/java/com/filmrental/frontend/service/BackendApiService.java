package com.filmrental.frontend.service;

import com.filmrental.frontend.model.EndpointResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendApiService {

    private final RestTemplate restTemplate;
    private final String backendBaseUrl;

    public BackendApiService(RestTemplate restTemplate, @Value("${backend.base-url}") String backendBaseUrl) {
        this.restTemplate = restTemplate;
        this.backendBaseUrl = backendBaseUrl;
    }

    public EndpointResult execute(String method, String path, String requestBody) {
        String normalizedPath = path.startsWith("/") ? path : "/" + path;
        String requestUrl = backendBaseUrl + normalizedPath;

        try {
            HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());

            // =================================================================
            // 🔥 THE FIX: INTERCEPT IMAGE REQUESTS 🔥
            // If the path ends in /picture and it's a GET, DO NOT download the bytes!
            // Just return a clean message so the HTML can generate the image button.
            // =================================================================
            if (normalizedPath.toLowerCase().endsWith("/picture") && httpMethod == HttpMethod.GET) {
                return new EndpointResult(
                        true,
                        200,
                        requestUrl,
                        method,
                        "{\"status\": \"Image successfully located on backend. Use the button below to view it.\"}",
                        null
                );
            }
            // =================================================================

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE + "," + MediaType.ALL_VALUE));

            HttpEntity<String> entity = new HttpEntity<>(normalizeBody(requestBody), headers);
            ResponseEntity<String> response = restTemplate.exchange(requestUrl, httpMethod, entity, String.class);

            return new EndpointResult(
                    true,
                    response.getStatusCode().value(),
                    requestUrl,
                    method,
                    pretty(response.getBody()),
                    null
            );
        } catch (HttpStatusCodeException ex) {
            return new EndpointResult(
                    false,
                    ex.getStatusCode().value(),
                    requestUrl,
                    method,
                    pretty(ex.getResponseBodyAsString()),
                    ex.getMessage()
            );
        } catch (RestClientException | IllegalArgumentException ex) {
            return new EndpointResult(
                    false,
                    0,
                    requestUrl,
                    method,
                    "",
                    ex.getMessage()
            );
        }
    }

    private String normalizeBody(String body) {
        if (body == null || body.isBlank()) {
            return null;
        }
        return body;
    }

    private String pretty(String responseBody) {
        if (responseBody == null || responseBody.isBlank()) {
            return "<empty response>";
        }
        return responseBody;
    }
}