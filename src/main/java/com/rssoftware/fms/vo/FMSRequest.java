package com.rssoftware.fms.vo;

import java.io.Serializable;
import java.util.List;

import com.rssoftware.fms.model.FMSRuleType;
import com.rssoftware.fms.model.FMSTransaction;

public class FMSRequest implements Serializable {

	private final static long serialVersionUID = 1L;

	private String merchantId;

	private String email;

	private String cardNo;

	private String ip;

	private String strAddr;

	private String countryCode;

	private String word;

	private String avsZip;

	private String avsStrAddr;

	private String avsCity;

	private String avsState;

	private String geoIp;

	private List<FMSTransaction> fmsTransactions;

	private String deviceId;

	private FMSRuleType fmsRuleType;

	public FMSRequest() {

	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
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

	public String getGeoIp() {
		return geoIp;
	}

	public void setGeoIp(String geoIp) {
		this.geoIp = geoIp;
	}

	public List<FMSTransaction> getFmsTransactions() {
		return fmsTransactions;
	}

	public void setFmsTransactions(List<FMSTransaction> fmsTransactions) {
		this.fmsTransactions = fmsTransactions;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public FMSRuleType getFmsRuleType() {
		return fmsRuleType;
	}

	public void setFmsRuleType(FMSRuleType fmsRuleType) {
		this.fmsRuleType = fmsRuleType;
	}

	@Override
	public String toString() {
		return "FMSRequest [merchantId=" + merchantId + ", email=" + email + ", cardNo=" + cardNo + ", ip=" + ip
				+ ", strAddr=" + strAddr + ", countryCode=" + countryCode + ", word=" + word + ", avsZip=" + avsZip
				+ ", avsStrAddr=" + avsStrAddr + ", avsCity=" + avsCity + ", avsState=" + avsState + ", geoIp=" + geoIp
				+ ", fmsTransactions=" + fmsTransactions + ", deviceId=" + deviceId + ", fmsRuleType=" + fmsRuleType
				+ "]";
	}

}
