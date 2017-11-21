package com.get.edgepay.fms.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.get.edgepay.fms.constant.CacheConstant;
import com.get.edgepay.fms.dao.FMSRulePostgresDao;
import com.get.edgepay.fms.dao.FMSRuleTypePostgresDao;
import com.get.edgepay.fms.dao.FMSTxnPostgresDao;
import com.get.edgepay.fms.dto.FMSRuleCacheDto;
import com.get.edgepay.fms.dto.FMSRuleTypeCacheDto;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.model.FMSTransaction;
import com.get.edgepay.fms.util.CacheUtil;

@Component
public class FMSFacadeImpl implements FMSFacade {

	private static final Logger log = LoggerFactory.getLogger(FMSFacadeImpl.class);

	private static final String ALL_RULETYPES = CacheConstant.ALL_RULETYPES.name();

	private static final String PUB_RULES = CacheConstant.PUB_RULES.name();

	private static final String PRI_RULES = CacheConstant.PRI_RULES.name();

	@Autowired
	private CacheUtil cacheUtil;

	@Autowired
	private FMSRuleTypePostgresDao fmsRuleTypePostgresDao;

	@Autowired
	private FMSRulePostgresDao fmsRulePostgresDao;

	@Autowired
	private FMSTxnPostgresDao fmsTxnPostgresDao;
	
	@Override
	public int saveRule(List<FMSRule> fmsRules) throws Exception {
		int response = fmsRulePostgresDao.insert(fmsRules);
		//TODO: update PUB_RULES, PRI_RULES in cache...
		return response;
	}

	@Override
	public int saveRuleType(FMSRuleType fmsRuleType) throws Exception {
		int response = fmsRuleTypePostgresDao.insert(fmsRuleType);
		if (response > 0) {
			List<FMSRuleType> allRuleTypeList = fmsRuleTypePostgresDao.getAll();
			cacheUtil.removeFromCache(ALL_RULETYPES, FMSRuleTypeCacheDto.class);
			cacheUtil.addToCache(ALL_RULETYPES, ALL_RULETYPES, allRuleTypeList);
		}
		return response;
	}

	@Override
	public List<FMSRuleType> fetchAllRuleTypes() throws Exception {
		List<FMSRuleType> allRuleTypeList = (List<FMSRuleType>) cacheUtil.getFromCache(ALL_RULETYPES, ALL_RULETYPES, FMSRuleTypeCacheDto.class);
		return allRuleTypeList;
	}

	@Override
	public List<FMSRule> getPubRuleDetails() {
		List<FMSRule> pubRuleDetails = (List<FMSRule>) cacheUtil.getFromCache(PUB_RULES, PUB_RULES, FMSRuleCacheDto.class);
		return pubRuleDetails;
	}

	@Override
	public List<FMSRule> getPriRuleDetails() {
		List<FMSRule> priRuleDetails = (List<FMSRule>) cacheUtil.getFromCache(PRI_RULES, PRI_RULES, FMSRuleCacheDto.class);
		return priRuleDetails;
	}

	@Override
	public List<Object> saveOrUpdateFMSTxn(FMSTransaction fmsTransaction) throws Exception {
		List<Object> response = fmsTxnPostgresDao.upsert(fmsTransaction);
		return response;
	}
}
