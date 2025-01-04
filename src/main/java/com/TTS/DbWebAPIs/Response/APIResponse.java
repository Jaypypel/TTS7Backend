package com.TTS.DbWebAPIs.Response;



public class APIResponse<T> {
    String message;
    T body;

    public APIResponse(String message, T body) {
        this.message = message;
        this.body = body;
    }

    public APIResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
