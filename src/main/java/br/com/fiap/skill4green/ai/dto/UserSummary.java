package br.com.fiap.skill4green.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class UserSummary {
    private UserInfo user;
    private String department;
    private List<String> skills;

    @JsonProperty("recent_tasks")
    private List<RecentTask> recentTasks;

    private Goals goals;
    private Map<String, Object> extra;

    public UserSummary() {}

    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public List<RecentTask> getRecentTasks() { return recentTasks; }
    public void setRecentTasks(List<RecentTask> recentTasks) { this.recentTasks = recentTasks; }

    public Goals getGoals() { return goals; }
    public void setGoals(Goals goals) { this.goals = goals; }

    public Map<String, Object> getExtra() { return extra; }
    public void setExtra(Map<String, Object> extra) { this.extra = extra; }
}