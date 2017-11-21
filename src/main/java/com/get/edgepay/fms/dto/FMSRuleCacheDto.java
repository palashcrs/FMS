package com.get.edgepay.fms.dto;

import java.io.Serializable;
import java.util.List;

import com.get.edgepay.fms.model.FMSRule;

public class FMSRuleCacheDto implements Serializable {

	private final static long serialVersionUID = 1L;

	private List<FMSRule> ruleList;

	public List<FMSRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<FMSRule> ruleList) {
		this.ruleList = ruleList;
	}

}
