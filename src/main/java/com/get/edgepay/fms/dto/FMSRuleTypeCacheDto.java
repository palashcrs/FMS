package com.get.edgepay.fms.dto;

import java.io.Serializable;
import java.util.List;

import com.get.edgepay.fms.model.FMSRuleType;

public class FMSRuleTypeCacheDto implements Serializable {

	private final static long serialVersionUID = 1L;

	private List<FMSRuleType> ruleTypeList;

	public List<FMSRuleType> getRuleTypeList() {
		return ruleTypeList;
	}

	public void setRuleTypeList(List<FMSRuleType> ruleTypeList) {
		this.ruleTypeList = ruleTypeList;
	}

}
