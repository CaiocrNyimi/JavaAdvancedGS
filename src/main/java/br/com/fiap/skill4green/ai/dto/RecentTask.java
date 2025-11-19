package br.com.fiap.skill4green.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecentTask {
    @JsonProperty("task_code")
    private String taskCode;

    private int count;

    public RecentTask() {}

    public RecentTask(String taskCode, int count) {
        this.taskCode = taskCode;
        this.count = count;
    }

    public String getTaskCode() { return taskCode; }
    public void setTaskCode(String taskCode) { this.taskCode = taskCode; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}