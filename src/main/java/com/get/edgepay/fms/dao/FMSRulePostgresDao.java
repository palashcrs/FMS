package com.get.edgepay.fms.dao;

import java.util.List;

import com.get.edgepay.fms.model.FMSRule;

public interface FMSRulePostgresDao {

	List<FMSRule> getPubRuleDetails();

	List<FMSRule> getPriRuleDetails();
}
