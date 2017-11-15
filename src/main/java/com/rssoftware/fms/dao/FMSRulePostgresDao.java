package com.rssoftware.fms.dao;

import java.util.List;

import com.rssoftware.fms.model.FMSRuleDetails;

public interface FMSRulePostgresDao {

	List<FMSRuleDetails> getPubRuleDetails();

	List<FMSRuleDetails> getPriRuleDetails();
}
