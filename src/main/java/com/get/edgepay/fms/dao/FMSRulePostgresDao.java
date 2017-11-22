package com.get.edgepay.fms.dao;

import java.util.List;

import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.model.FMSRule;

public interface FMSRulePostgresDao {

	int insert(List<FMSRule> fmsRules) throws Exception;

	List<FMSRule> getPubRules() throws Exception;

	List<FMSRule> getPriRules() throws Exception;
}
