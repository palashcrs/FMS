package com.get.edgepay.fms.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.get.edgepay.fms.facade.FMSFacade;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.service.FMSCacheService;

@Service
public class FMSCacheServiceImpl implements FMSCacheService {

	private static final Logger log = LoggerFactory.getLogger(FMSCacheServiceImpl.class);

	@Autowired
	private FMSFacade fmsFacade;

	/**
	 * Method to load all data from Cache on startup.
	 * @throws Exception
	 */
	@PostConstruct
	public void loadAll() throws Exception {
		log.info("******Reload all data on startup!");
		loadAllRuleTypes();
		loadAllPubRules();
		loadAllPriRules();
		// loadAllTransactions();
	}

	@Override
	public void loadAllRuleTypes() throws Exception {
		fmsFacade.removeRuleTypesFromCache();
		List<FMSRuleType> allRuleTypeList = fmsFacade.fetchAllRuleTypesFromDB();
		fmsFacade.saveRuleTypeInCache(allRuleTypeList);
	}

	@Override
	public void loadAllPubRules() throws Exception {
		fmsFacade.removePubRulesFromCache();
		List<FMSRule> pubRules = fmsFacade.getPubRulesFromDB();
		fmsFacade.savePubRuleInCache(pubRules);
	}

	@Override
	public void loadAllPriRules() throws Exception {
		fmsFacade.removePriRulesFromCache();
		List<FMSRule> priRules = fmsFacade.getPriRulesFromDB();
		fmsFacade.savePriRuleInCache(priRules);
	}

	@Override
	public void loadAllTransactions() {
		// TODO Auto-generated method stub
	}

}
