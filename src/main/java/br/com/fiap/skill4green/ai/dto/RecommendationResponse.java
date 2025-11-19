package br.com.fiap.skill4green.ai.dto;

import java.util.List;

public class RecommendationResponse {
    private List<String> items;

    public RecommendationResponse() {}

    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
}