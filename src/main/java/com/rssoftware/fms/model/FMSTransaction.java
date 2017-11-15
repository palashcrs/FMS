package com.rssoftware.fms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class FMSTransaction implements Serializable {

	private final static long serialVersionUID = 1L;

	private Integer fmsTxnId;

	private String edgePayTxnId;

	private String txnType;

	private BigDecimal txnTotalAmt;

	private String edgePayTxnStatus;

	private String fmsTxnStatus;

	private String email;

	private String cardNo;

	private String ip;

	private String strAddr;

	private String custName;

	private String avsZip;

	private String avsStrAddr;

	private String avsCity;

	private String avsState;

	private String geoIp;

	private String deviceId;

	private String createdBy;

	private Timestamp creationTs;

	private String updatedBy;

	private Timestamp updateTs;

	public FMSTransaction() {

	}

	public Integer getFmsTxnId() {
		return fmsTxnId;
	}

	public void setFmsTxnId(Integer fmsTxnId) {
		this.fmsTxnId = fmsTxnId;
	}

	public String getEdgePayTxnId() {
		return edgePayTxnId;
	}

	public void setEdgePayTxnId(String edgePayTxnId) {
		this.edgePayTxnId = edgePayTxnId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public BigDecimal getTxnTotalAmt() {
		return txnTotalAmt;
	}

	public void setTxnTotalAmt(BigDecimal txnTotalAmt) {
		this.txnTotalAmt = txnTotalAmt;
	}

	public String getEdgePayTxnStatus() {
		return edgePayTxnStatus;
	}

	public void setEdgePayTxnStatus(String edgePayTxnStatus) {
		this.edgePayTxnStatus = edgePayTxnStatus;
	}

	public String getFmsTxnStatus() {
		return fmsTxnStatus;
	}

	public void setFmsTxnStatus(String fmsTxnStatus) {
		this.fmsTxnStatus = fmsTxnStatus;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Timestamp updateTs) {
		this.updateTs = updateTs;
	}

	@Override
	public String toString() {
		return "FMSTransaction [fmsTxnId=" + fmsTxnId + ", edgePayTxnId=" + edgePayTxnId + ", txnType=" + txnType
				+ ", txnTotalAmt=" + txnTotalAmt + ", edgePayTxnStatus=" + edgePayTxnStatus + ", fmsTxnStatus="
				+ fmsTxnStatus + ", email=" + email + ", cardNo=" + cardNo + ", ip=" + ip + ", strAddr=" + strAddr
				+ ", custName=" + custName + ", avsZip=" + avsZip + ", avsStrAddr=" + avsStrAddr + ", avsCity="
				+ avsCity + ", avsState=" + avsState + ", geoIp=" + geoIp + ", deviceId=" + deviceId + ", createdBy="
				+ createdBy + ", creationTs=" + creationTs + ", updatedBy=" + updatedBy + ", updateTs=" + updateTs
				+ "]";
	}

}
