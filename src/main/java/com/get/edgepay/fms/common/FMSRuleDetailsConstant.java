package com.get.edgepay.fms.common;

public enum FMSRuleDetailsConstant {

	RULETYPE_EMAIL("EMAIL"), RULETYPE_CARDNO("CARDNO"), RULETYPE_WORD("WORD"),

	RULETYPE_ACCESSMODE_REVIEW("REVIEW"), RULETYPE_ACCESSMODE_DECLINE("DECLINE"),

	RULETYPE_ACTION_R("R"), RULETYPE_ACTION_D("D");

	private String ruleTypeValue;

	private FMSRuleDetailsConstant(String ruleTypeValue) {
		this.ruleTypeValue = ruleTypeValue;
	}

	public String getRuleTypeValue() {
		return ruleTypeValue;
	}

}
