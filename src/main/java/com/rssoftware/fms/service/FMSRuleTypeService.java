package com.rssoftware.fms.service;

import java.util.List;

import com.rssoftware.fms.model.FMSRuleType;

public interface FMSRuleTypeService {

	int createNewRuleType(FMSRuleType fmsRuleType) throws Exception;
	
	public List<FMSRuleType> getAllRuleTypes() throws Exception;
}
