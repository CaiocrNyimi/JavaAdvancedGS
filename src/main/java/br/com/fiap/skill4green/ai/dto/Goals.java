package br.com.fiap.skill4green.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Goals {
    @JsonProperty("dept_kwh_reduction_pct")
    private Double deptKwhReductionPct;

    public Goals() {}

    public Goals(Double deptKwhReductionPct) {
        this.deptKwhReductionPct = deptKwhReductionPct;
    }

    public Double getDeptKwhReductionPct() { return deptKwhReductionPct; }
    public void setDeptKwhReductionPct(Double deptKwhReductionPct) { this.deptKwhReductionPct = deptKwhReductionPct; }
}