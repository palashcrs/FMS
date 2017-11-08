package com.rssoftware.fms.facade;

import java.util.List;

import com.rssoftware.fms.model.FMSRuleDetails;
import com.rssoftware.fms.model.FMSRuleType;

public interface FMSFacade {

	List<FMSRuleDetails> getPubRuleDetails();

	List<FMSRuleDetails> getPriRuleDetails();

	int saveRuleType(FMSRuleType fmsRuleType) throws Exception;

	List<FMSRuleType> fetchAllRuleTypes() throws Exception;

}
