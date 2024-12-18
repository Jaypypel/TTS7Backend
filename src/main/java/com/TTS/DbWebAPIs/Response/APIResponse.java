package com.TTS.DbWebAPIs.Response;

public class APIResponse {
    String message;
    Object body;

    public APIResponse(String message, Object body) {
        this.message = message;
        this.body = body;
    }

    public APIResponse() {
    }

    public String getMessage() {
        return message;
    }git

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
