package com.rssoftware.fms.dao;

import java.util.List;

import com.rssoftware.fms.model.FMSRuleType;

public interface FMSRuleTypePostgresDao {

	int insert(FMSRuleType fmsRuleType) throws Exception;

	List<FMSRuleType> getAll() throws Exception;
}
