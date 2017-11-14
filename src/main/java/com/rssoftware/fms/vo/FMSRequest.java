package com.rssoftware.fms.vo;

import java.io.Serializable;
import java.util.List;

import com.rssoftware.fms.model.FMSRule;
import com.rssoftware.fms.model.FMSRuleType;
import com.rssoftware.fms.model.FMSTransaction;

public class FMSRequest implements Serializable {

	private final static long serialVersionUID = 1L;

	private String merchantId;

	private FMSRuleType fmsRuleType;

	private List<FMSRule> fmsRules;

	private List<FMSTransaction> fmsTransactions;

	public FMSRequest() {

	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public FMSRuleType getFmsRuleType() {
		return fmsRuleType;
	}

	public void setFmsRuleType(FMSRuleType fmsRuleType) {
		this.fmsRuleType = fmsRuleType;
	}

	public List<FMSRule> getFmsRules() {
		return fmsRules;
	}

	public void setFmsRules(List<FMSRule> fmsRules) {
		this.fmsRules = fmsRules;
	}

	public List<FMSTransaction> getFmsTransactions() {
		return fmsTransactions;
	}

	public void setFmsTransactions(List<FMSTransaction> fmsTransactions) {
		this.fmsTransactions = fmsTransactions;
	}

	@Override
	public String toString() {
		return "FMSRequest [merchantId=" + merchantId + ", fmsRuleType=" + fmsRuleType + ", fmsRules=" + fmsRules
				+ ", fmsTransactions=" + fmsTransactions + ", getMerchantId()=" + getMerchantId()
				+ ", getFmsRuleType()=" + getFmsRuleType() + ", getFmsRules()=" + getFmsRules()
				+ ", getFmsTransactions()=" + getFmsTransactions() + "]";
	}

}
