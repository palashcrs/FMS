package com.get.edgepay.fms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class FMSRule implements Serializable {

	private final static long serialVersionUID = 1L;

	private Integer ruleId;

	private Integer ruleTypeId;

	private String email;

	private String cardNo;

	private String strAddr;

	private String ip;

	private String word;

	private Integer cardLimit;

	private Integer ipLimit;

	private BigDecimal maxAmtLimit;

	private String timePeriod;

	private String avsZip;

	private String avsStrAddr;

	private String avsCity;

	private String avsState;

	private String avsResult;

	private String geoIp;

	private String deviceId;

	private String createdBy;

	private Timestamp creationTs;

	public FMSRule() {

	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(Integer ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStrAddr() {
		return strAddr;
	}

	public void setStrAddr(String strAddr) {
		this.strAddr = strAddr;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(Integer cardLimit) {
		this.cardLimit = cardLimit;
	}

	public Integer getIpLimit() {
		return ipLimit;
	}

	public void setIpLimit(Integer ipLimit) {
		this.ipLimit = ipLimit;
	}

	public BigDecimal getMaxAmtLimit() {
		return maxAmtLimit;
	}

	public void setMaxAmtLimit(BigDecimal maxAmtLimit) {
		this.maxAmtLimit = maxAmtLimit;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getAvsZip() {
		return avsZip;
	}

	public void setAvsZip(String avsZip) {
		this.avsZip = avsZip;
	}

	public String getAvsStrAddr() {
		return avsStrAddr;
	}

	public void setAvsStrAddr(String avsStrAddr) {
		this.avsStrAddr = avsStrAddr;
	}

	public String getAvsCity() {
		return avsCity;
	}

	public void setAvsCity(String avsCity) {
		this.avsCity = avsCity;
	}

	public String getAvsState() {
		return avsState;
	}

	public void setAvsState(String avsState) {
		this.avsState = avsState;
	}

	public String getAvsResult() {
		return avsResult;
	}

	public void setAvsResult(String avsResult) {
		this.avsResult = avsResult;
	}

	public String getGeoIp() {
		return geoIp;
	}

	public void setGeoIp(String geoIp) {
		this.geoIp = geoIp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreationTs() {
		return creationTs;
	}

	public void setCreationTs(Timestamp creationTs) {
		this.creationTs = creationTs;
	}

	@Override
	public String toString() {
		return "FMSRule [ruleId=" + ruleId + ", ruleTypeId=" + ruleTypeId + ", email=" + email + ", cardNo=" + cardNo
				+ ", strAddr=" + strAddr + ", ip=" + ip + ", word=" + word + ", cardLimit=" + cardLimit + ", ipLimit="
				+ ipLimit + ", maxAmtLimit=" + maxAmtLimit + ", timePeriod=" + timePeriod + ", avsZip=" + avsZip
				+ ", avsStrAddr=" + avsStrAddr + ", avsCity=" + avsCity + ", avsState=" + avsState + ", avsResult="
				+ avsResult + ", geoIp=" + geoIp + ", deviceId=" + deviceId + ", createdBy=" + createdBy
				+ ", creationTs=" + creationTs + "]";
	}

}
