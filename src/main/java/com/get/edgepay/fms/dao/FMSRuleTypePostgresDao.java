package com.get.edgepay.fms.dao;

import java.util.List;

import com.get.edgepay.fms.model.FMSRuleType;

public interface FMSRuleTypePostgresDao {

	int insert(FMSRuleType fmsRuleType) throws Exception;

	List<FMSRuleType> getAll() throws Exception;
}
