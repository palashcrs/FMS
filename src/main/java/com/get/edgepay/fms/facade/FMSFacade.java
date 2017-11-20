package com.get.edgepay.fms.facade;

import java.util.List;

import com.get.edgepay.fms.model.FMSRuleDetails;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.model.FMSTransaction;

public interface FMSFacade {

	List<FMSRuleDetails> getPubRuleDetails();

	List<FMSRuleDetails> getPriRuleDetails();

	int saveRuleType(FMSRuleType fmsRuleType) throws Exception;

	List<FMSRuleType> fetchAllRuleTypes() throws Exception;

	List<Object> saveOrUpdateFMSTxn(FMSTransaction fmsTransaction) throws Exception;
}
