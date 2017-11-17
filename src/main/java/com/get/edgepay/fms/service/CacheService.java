package com.get.edgepay.fms.service;

public interface CacheService {

	public void loadAllRuleTypes() throws Exception;

	public void loadAllRules();

	public void loadAllPubRuleDetails();

	public void loadAllPriRuleDetails();

	public void loadAllTransactions();

}
