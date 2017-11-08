package com.rssoftware.fms.model;

import java.io.Serializable;

public class FMSRuleType implements Serializable {

	private final static long serialVersionUID = 1L;

	private Integer ruleTypeId;

	private String ruleType;

	private String accessMode;

	private String action;

	public FMSRuleType() {

	}

	public Integer getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(Integer ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "FMSRuleType [ruleTypeId=" + ruleTypeId + ", ruleType=" + ruleType + ", accessMode=" + accessMode
				+ ", action=" + action + "]";
	}

}
