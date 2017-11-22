package com.get.edgepay.fms.service;

import java.util.List;

import com.get.edgepay.fms.model.FMSRule;

public interface FMSRuleService {

	int createRule(List<FMSRule> fmsRules) throws Exception;

	List<FMSRule> getAllRuleS() throws Exception;
}
