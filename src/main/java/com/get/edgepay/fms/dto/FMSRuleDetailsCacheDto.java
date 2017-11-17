package com.get.edgepay.fms.dto;

import java.io.Serializable;
import java.util.List;

import com.get.edgepay.fms.model.FMSRuleDetails;

public class FMSRuleDetailsCacheDto implements Serializable {

	private final static long serialVersionUID = 1L;

	private List<FMSRuleDetails> ruleDetailsList;

	public List<FMSRuleDetails> getRuleDetailsList() {
		return ruleDetailsList;
	}

	public void setRuleDetailsList(List<FMSRuleDetails> ruleDetailsList) {
		this.ruleDetailsList = ruleDetailsList;
	}

}
