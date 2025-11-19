package br.com.fiap.skill4green.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
    private String id;
    private String name;

    public UserInfo() {}

    public UserInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty("id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @JsonProperty("name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}