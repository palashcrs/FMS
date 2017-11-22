package com.get.edgepay.fms.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.get.edgepay.fms.constant.CacheConstant;
import com.get.edgepay.fms.dao.FMSRulePostgresDao;
import com.get.edgepay.fms.dao.FMSRuleTypePostgresDao;
import com.get.edgepay.fms.dto.FMSRuleCacheDto;
import com.get.edgepay.fms.dto.FMSRuleTypeCacheDto;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.service.CacheService;
import com.get.edgepay.fms.util.CacheUtil;

@Service
public class CacheServiceImpl implements CacheService {

	private static final Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

	private static final String ALL_RULETYPES = CacheConstant.ALL_RULETYPES.name();

	private static final String PUB_RULES = CacheConstant.PUB_RULES.name();

	private static final String PRI_RULES = CacheConstant.PRI_RULES.name();

	@Autowired
	private CacheUtil cacheUtil;

	@Autowired
	FMSRuleTypePostgresDao fmsRuleTypePostgresDao;

	@Autowired
	private FMSRulePostgresDao fmsRulePostgresDao;

	/**
	 * Method to load all data from Cache on startup.
	 * @throws Exception
	 */
	@PostConstruct
	public void loadAll() throws Exception {
		log.info("******Reload all data on startup!");
		loadAllRuleTypes();
		// loadAllRules();
		loadAllPubRules();
		loadAllPriRules();
		// loadAllTransactions();
	}

	@Override
	public void loadAllRuleTypes() throws Exception {
		List<FMSRuleType> allRuleTypeList = fmsRuleTypePostgresDao.getAll();
		cacheUtil.removeFromCache(ALL_RULETYPES, FMSRuleTypeCacheDto.class);
		cacheUtil.addToCache(ALL_RULETYPES, ALL_RULETYPES, allRuleTypeList);
	}

	@Override
	public void loadAllRules() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadAllPubRules() throws Exception {
		List<FMSRule> pubRuleDetails = fmsRulePostgresDao.getPubRules();
		cacheUtil.removeFromCache(PUB_RULES, FMSRuleCacheDto.class);
		cacheUtil.addToCache(PUB_RULES, PUB_RULES, pubRuleDetails);

	}

	@Override
	public void loadAllPriRules() throws Exception {
		List<FMSRule> priRuleDetails = fmsRulePostgresDao.getPriRules();
		cacheUtil.removeFromCache(PRI_RULES, FMSRuleCacheDto.class);
		cacheUtil.addToCache(PRI_RULES, PRI_RULES, priRuleDetails);
	}

	@Override
	public void loadAllTransactions() {
		// TODO Auto-generated method stub

	}

}
