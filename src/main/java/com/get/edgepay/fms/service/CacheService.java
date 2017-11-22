package com.get.edgepay.fms.service;

public interface CacheService {

	public void loadAllRuleTypes() throws Exception;

	public void loadAllRules();

	public void loadAllPubRules() throws Exception;

	public void loadAllPriRules() throws Exception;

	public void loadAllTransactions();

}
