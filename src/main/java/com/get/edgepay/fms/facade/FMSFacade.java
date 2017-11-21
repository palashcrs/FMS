package com.get.edgepay.fms.facade;

import java.util.List;

import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.model.FMSTransaction;

public interface FMSFacade {

	List<FMSRule> getPubRuleDetails();

	List<FMSRule> getPriRuleDetails();

	int saveRuleType(FMSRuleType fmsRuleType) throws Exception;

	List<FMSRuleType> fetchAllRuleTypes() throws Exception;

	List<Object> saveOrUpdateFMSTxn(FMSTransaction fmsTransaction) throws Exception;
}
