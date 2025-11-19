package br.com.fiap.skill4green.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MotivationReq {
    @JsonProperty("task_code")
    private String taskCode;

    private Integer executions;
    private String name;

    private Double kwh;
    private Double co2;
    private Double cost;

    public MotivationReq() {}

    public String getTaskCode() { return taskCode; }
    public void setTaskCode(String taskCode) { this.taskCode = taskCode; }

    public Integer getExecutions() { return executions; }
    public void setExecutions(Integer executions) { this.executions = executions; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getKwh() { return kwh; }
    public void setKwh(Double kwh) { this.kwh = kwh; }

    public Double getCo2() { return co2; }
    public void setCo2(Double co2) { this.co2 = co2; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}