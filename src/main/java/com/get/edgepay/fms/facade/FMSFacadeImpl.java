package com.get.edgepay.fms.facade;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.get.edgepay.fms.constant.FMSCacheConstant;
import com.get.edgepay.fms.dao.FMSRulePostgresDao;
import com.get.edgepay.fms.dao.FMSRuleTypePostgresDao;
import com.get.edgepay.fms.dao.FMSTxnPostgresDao;
import com.get.edgepay.fms.dto.FMSRuleCacheDto;
import com.get.edgepay.fms.dto.FMSRuleTypeCacheDto;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.model.FMSTransaction;
import com.get.edgepay.fms.util.CacheUtil;
import com.get.edgepay.fms.util.FMSUtil;

@Component
public class FMSFacadeImpl implements FMSFacade {

	private static final Logger log = LoggerFactory.getLogger(FMSFacadeImpl.class);

	private static final String ALL_RULETYPES = FMSCacheConstant.ALL_RULETYPES.name();

	private static final String PUB_RULES = FMSCacheConstant.PUB_RULES.name();

	private static final String PRI_RULES = FMSCacheConstant.PRI_RULES.name();

	@Autowired
	private CacheUtil cacheUtil;

	@Autowired
	private FMSRuleTypePostgresDao fmsRuleTypePostgresDao;

	@Autowired
	private FMSRulePostgresDao fmsRulePostgresDao;

	@Autowired
	private FMSTxnPostgresDao fmsTxnPostgresDao;
	
	// ***************************RULE*****************

	@Override
	public int saveRule(List<FMSRule> fmsRules) throws Exception {
		int response = saveRuleInDB(fmsRules);
		if (response > 0) {
			removePubRulesFromCache();
			removePriRulesFromCache();
			List<FMSRule> pubRules = getPubRulesFromDB();
			List<FMSRule> priRules = getPriRulesFromDB();
			savePubRuleInCache(pubRules);
			savePriRuleInCache(priRules);
		}
		return response;
	}
	
	@Override
	public int saveRuleInDB(List<FMSRule> fmsRules) throws Exception {
		int response = fmsRulePostgresDao.insert(fmsRules);
		return response;
	}

	@Override
	public void savePubRuleInCache(List<FMSRule> fmsPubRules) {
		cacheUtil.addToCache(PUB_RULES, PUB_RULES, fmsPubRules);
	}
	
	@Override
	public void savePriRuleInCache(List<FMSRule> fmsPriRules) {
		cacheUtil.addToCache(PRI_RULES, PRI_RULES, fmsPriRules);
	}
	
	@Override
	public List<FMSRule> getPubRulesFromCache() {
		List<FMSRule> pubRules = (List<FMSRule>) cacheUtil.getFromCache(PUB_RULES, PUB_RULES, FMSRuleCacheDto.class);
		return pubRules;
	}

	@Override
	public List<FMSRule> getPubRulesFromDB() throws Exception {
		List<FMSRule> pubRules = fmsRulePostgresDao.getPubRules();
		return pubRules;
	}
	
	@Override
	public List<FMSRule> getPriRulesFromCache() {
		List<FMSRule> priRules = (List<FMSRule>) cacheUtil.getFromCache(PRI_RULES, PRI_RULES, FMSRuleCacheDto.class);
		return priRules;
	}
	
	@Override
	public List<FMSRule> getPriRulesFromDB() throws Exception {
		List<FMSRule> priRules = fmsRulePostgresDao.getPriRules();
		return priRules;
	}
	
	@Override
	public void removePubRulesFromCache() {
		cacheUtil.removeFromCache(PUB_RULES, FMSRuleCacheDto.class);
	}
	
	@Override
	public void removePriRulesFromCache() {
		cacheUtil.removeFromCache(PRI_RULES, FMSRuleCacheDto.class);
	}

	@Override
	public List<FMSRule> getAllRules() throws Exception {
		List<FMSRule> fmsAllRules = null;
		List<FMSRule> fmsPubRules = null;
		List<FMSRule> fmsPriRules = null;
		
		ExecutorService execotorService = Executors.newFixedThreadPool(2);
		
		if (!execotorService.isShutdown()) {
			Future<List<FMSRule>> future = execotorService.submit(new Callable<List<FMSRule>>() {

				@Override
				public List<FMSRule> call() throws Exception {
					List<FMSRule> pubRules = getPubRulesFromCache();
					return pubRules;
				}
			});
			fmsPubRules = future.get();
		}
		
		if (!execotorService.isShutdown()) {
			Future<List<FMSRule>> future = execotorService.submit(new Callable<List<FMSRule>>() {

				@Override
				public List<FMSRule> call() throws Exception {
					List<FMSRule> priRules = getPriRulesFromCache();
					return priRules;
				}
			});
			fmsPriRules = future.get();
		}
		
		execotorService.shutdown();
		
		if (execotorService.isShutdown()) {
			fmsAllRules = FMSUtil.getInstance().mergeLists(fmsPubRules, fmsPriRules);
		}
		
		return fmsAllRules;
	}
	
	// ***************************RULETYPE*****************

	@Override
	public int saveRuleType(FMSRuleType fmsRuleType) throws Exception {
		int response = saveRuleTypeInDB(fmsRuleType);
		if (response > 0) {
			removeRuleTypesFromCache();
			List<FMSRuleType> allRuleTypeList = fetchAllRuleTypesFromDB();
			saveRuleTypeInCache(allRuleTypeList);
		}
		return response;
	}
	
	@Override
	public int saveRuleTypeInDB(FMSRuleType fmsRuleType) throws Exception {
		int response = fmsRuleTypePostgresDao.insert(fmsRuleType);
		return response;
	}

	@Override
	public void saveRuleTypeInCache(List<FMSRuleType> allRuleTypeList) {
		cacheUtil.addToCache(ALL_RULETYPES, ALL_RULETYPES, allRuleTypeList);
	}

	@Override
	public List<FMSRuleType> fetchAllRuleTypesFromCache() {
		List<FMSRuleType> allRuleTypeList = (List<FMSRuleType>) cacheUtil.getFromCache(ALL_RULETYPES, ALL_RULETYPES, FMSRuleTypeCacheDto.class);
		return allRuleTypeList;
	}

	@Override
	public List<FMSRuleType> fetchAllRuleTypesFromDB() throws Exception {
		List<FMSRuleType> allRuleTypeList = fmsRuleTypePostgresDao.getAll();
		return allRuleTypeList;
	}

	@Override
	public void removeRuleTypesFromCache() {
		cacheUtil.removeFromCache(ALL_RULETYPES, FMSRuleTypeCacheDto.class);
	}
	
	// ***************************TRANSACTION*****************
	
	@Override
	public List<Object> saveOrUpdateFMSTxn(FMSTransaction fmsTransaction) throws Exception {
		List<Object> response = saveOrUpdateFMSTxnInDB(fmsTransaction);
		//TODO: Cache update.
		return response;
	}

	@Override
	public List<Object> saveOrUpdateFMSTxnInDB(FMSTransaction fmsTransaction) throws Exception {
		List<Object> response = fmsTxnPostgresDao.upsert(fmsTransaction);
		return response;
	}

	@Override
	public void saveOrUpdateFMSTxnInCache(FMSTransaction fmsTransaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FMSTransaction> fetchAllFMSTransactionsFromDB() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FMSTransaction> fetchAllFMSTransactionsFromCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFMSTransactionsFromCache() {
		// TODO Auto-generated method stub
		
	}

}
