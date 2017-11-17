package com.get.edgepay.fms.service;

import java.util.List;

import com.get.edgepay.fms.model.FMSRuleType;

public interface FMSRuleTypeService {

	int createNewRuleType(FMSRuleType fmsRuleType) throws Exception;
	
	public List<FMSRuleType> getAllRuleTypes() throws Exception;
}
