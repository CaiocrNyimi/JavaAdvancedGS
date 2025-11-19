package br.com.fiap.skill4green.ai.dto;

import java.util.Map;

public class MotivationResponse {
    private String message;
    private Map<String, Object> computed;

    public MotivationResponse() {}

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Map<String, Object> getComputed() { return computed; }
    public void setComputed(Map<String, Object> computed) { this.computed = computed; }
}