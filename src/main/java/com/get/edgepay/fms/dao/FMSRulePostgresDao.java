package com.get.edgepay.fms.dao;

import java.util.List;

import com.get.edgepay.fms.model.FMSRuleDetails;

public interface FMSRulePostgresDao {

	List<FMSRuleDetails> getPubRuleDetails();

	List<FMSRuleDetails> getPriRuleDetails();
}
