package com.get.edgepay.fms.service;

public interface FMSCacheService {

	public void loadAllRuleTypes() throws Exception;

	public void loadAllPubRules() throws Exception;

	public void loadAllPriRules() throws Exception;

	public void loadAllTransactions();

}
