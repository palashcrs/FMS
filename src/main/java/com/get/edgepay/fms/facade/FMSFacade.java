package com.get.edgepay.fms.facade;

import java.util.List;

import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.model.FMSTransaction;

public interface FMSFacade {

	// ***************************RULE*****************

	int saveRule(List<FMSRule> fmsRules) throws Exception;

	int saveRuleInDB(List<FMSRule> fmsRules) throws Exception;

	void savePubRuleInCache(List<FMSRule> fmsRules);

	void savePriRuleInCache(List<FMSRule> fmsRules);

	List<FMSRule> getPubRulesFromCache();

	List<FMSRule> getPubRulesFromDB() throws Exception;

	void removePubRulesFromCache();

	List<FMSRule> getPriRulesFromCache();

	List<FMSRule> getPriRulesFromDB() throws Exception;

	void removePriRulesFromCache();

	List<FMSRule> getAllRules() throws Exception;

	// ***************************RULETYPE*****************

	int saveRuleType(FMSRuleType fmsRuleType) throws Exception;

	int saveRuleTypeInDB(FMSRuleType fmsRuleType) throws Exception;

	void saveRuleTypeInCache(List<FMSRuleType> allRuleTypeList);

	List<FMSRuleType> fetchAllRuleTypesFromDB() throws Exception;

	List<FMSRuleType> fetchAllRuleTypesFromCache();

	void removeRuleTypesFromCache();

	// ***************************TRANSACTION*****************

	List<Object> saveOrUpdateFMSTxn(FMSTransaction fmsTransaction) throws Exception;

	List<Object> saveOrUpdateFMSTxnInDB(FMSTransaction fmsTransaction) throws Exception;

	void saveOrUpdateFMSTxnInCache(FMSTransaction fmsTransaction);

	List<FMSTransaction> fetchAllFMSTransactionsFromDB() throws Exception;

	List<FMSTransaction> fetchAllFMSTransactionsFromCache();

	void removeFMSTransactionsFromCache();
}
