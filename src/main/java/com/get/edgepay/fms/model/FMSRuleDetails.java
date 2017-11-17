package com.get.edgepay.fms.model;

import java.io.Serializable;

public class FMSRuleDetails extends FMSRule implements Serializable {

	private final static long serialVersionUID = 1L;

	private String ruleType;

	private String accessMode;

	private String action;

	public FMSRuleDetails() {

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
		return "FMSRuleDetails [ruleType=" + ruleType + ", accessMode=" + accessMode + ", action=" + action
				+ ", getRuleId()=" + getRuleId() + ", getRuleTypeId()=" + getRuleTypeId() + ", getEmail()=" + getEmail()
				+ ", getCardNo()=" + getCardNo() + ", getIp()=" + getIp() + ", getStrAddr()=" + getStrAddr()
				+ ", getWord()=" + getWord() + ", getCardLimit()=" + getCardLimit() + ", getIpLimit()=" + getIpLimit()
				+ ", getMaxAmtLimit()=" + getMaxAmtLimit() + ", getTimePeriod()=" + getTimePeriod() + ", getAvsZip()="
				+ getAvsZip() + ", getAvsStrAddr()=" + getAvsStrAddr() + ", getAvsCity()=" + getAvsCity()
				+ ", getAvsState()=" + getAvsState() + ", getAvsResult()=" + getAvsResult() + ", getGeoIp()="
				+ getGeoIp() + ", getDeviceId()=" + getDeviceId() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreationTs()=" + getCreationTs() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
