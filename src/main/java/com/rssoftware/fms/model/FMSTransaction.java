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
				+ fmsTxnStatus + ", createdBy=" + createdBy + ", creationTs=" + creationTs + ", updatedBy=" + updatedBy
				+ ", updateTs=" + updateTs + "]";
	}

}
