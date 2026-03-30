package com.filmrental.frontend.model;

public class EndpointResult {

    private final boolean success;
    private final int statusCode;
    private final String requestUrl;
    private final String method;
    private final String responseBody;
    private final String errorMessage;

    public EndpointResult(boolean success, int statusCode, String requestUrl, String method, String responseBody, String errorMessage) {
        this.success = success;
        this.statusCode = statusCode;
        this.requestUrl = requestUrl;
        this.method = method;
        this.responseBody = responseBody;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
