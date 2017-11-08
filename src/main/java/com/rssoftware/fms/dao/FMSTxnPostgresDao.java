package com.rssoftware.fms.dao;

import java.util.List;

import com.rssoftware.fms.model.FMSRuleDetails;

public interface FMSTxnPostgresDao {

	List<FMSRuleDetails> getPubRuleDetails();

	List<FMSRuleDetails> getPriRuleDetails();

}
