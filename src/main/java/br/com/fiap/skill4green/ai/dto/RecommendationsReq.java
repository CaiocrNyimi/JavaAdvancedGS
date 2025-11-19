package br.com.fiap.skill4green.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecommendationsReq {
    @JsonProperty("user_summary")
    private UserSummary userSummary;

    @JsonProperty("max_items")
    private Integer maxItems;

    public RecommendationsReq() {}

    public UserSummary getUserSummary() { return userSummary; }
    public void setUserSummary(UserSummary userSummary) { this.userSummary = userSummary; }

    public Integer getMaxItems() { return maxItems; }
    public void setMaxItems(Integer maxItems) { this.maxItems = maxItems; }
}