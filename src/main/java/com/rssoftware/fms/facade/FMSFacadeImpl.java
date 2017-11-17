package com.rssoftware.fms.facade;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rssoftware.fms.dao.FMSRulePostgresDao;
import com.rssoftware.fms.dao.FMSRuleTypePostgresDao;
import com.rssoftware.fms.dao.FMSTxnPostgresDao;
import com.rssoftware.fms.model.FMSRuleDetails;
import com.rssoftware.fms.model.FMSRuleType;
import com.rssoftware.fms.model.FMSTransaction;
import com.rssoftware.fms.repository.CacheRepository;

@Component
public class FMSFacadeImpl implements FMSFacade {

	private static final Logger log = LoggerFactory.getLogger(FMSFacadeImpl.class);

	@Autowired
	private FMSRuleTypePostgresDao fmsRuleTypePostgresDao;

	@Autowired
	private FMSRulePostgresDao fmsRulePostgresDao;

	@Autowired
	private FMSTxnPostgresDao fmsTxnPostgresDao;

	@Autowired
	private CacheRepository cacheRepository;

	@Override
	public List<FMSRuleDetails> getPubRuleDetails() {

		List<FMSRuleDetails> fmsPubStatusList = (List<FMSRuleDetails>) cacheRepository.findByKey("PUB-RULES");

		if (fmsPubStatusList == null) {
			log.info("No data found in Cache, getting Public Rules from DB...");
			fmsPubStatusList = fmsRulePostgresDao.getPubRuleDetails();
		}

		return fmsPubStatusList;
	}

	@Override
	public List<FMSRuleDetails> getPriRuleDetails() {

		List<FMSRuleDetails> priRuleDetails = (List<FMSRuleDetails>) cacheRepository.findByKey("PRI-RULES");

		if (priRuleDetails == null) {
			log.info("No data found in Cache, getting Private Rules from DB...");
			priRuleDetails = fmsRulePostgresDao.getPriRuleDetails();
		}

		return priRuleDetails;
	}

	@Override
	public int saveRuleType(FMSRuleType fmsRuleType) throws Exception {

		// Save RuleType-list object in DB:
		int response = fmsRuleTypePostgresDao.insert(fmsRuleType);

		// Save RuleType-list object in Redis:
		List<FMSRuleType> allRuleTypeList = (List<FMSRuleType>) cacheRepository.findByKey("RT-ALL");
		if (allRuleTypeList == null) {
			allRuleTypeList = new ArrayList();
		}
		allRuleTypeList.add(fmsRuleType);
		cacheRepository.save("RT-ALL", allRuleTypeList);

		return response;
	}

	@Override
	public List<FMSRuleType> fetchAllRuleTypes() throws Exception {

		// List<FMSRuleType> allRuleTypeList = null;

		List<FMSRuleType> allRuleTypeList = (List<FMSRuleType>) cacheRepository.findByKey("RT-ALL");

		if (allRuleTypeList == null) {
			log.info("Cache null! Getting data from DB...");
			allRuleTypeList = fmsRuleTypePostgresDao.getAll();
		}

		return allRuleTypeList;
	}

	@Override
	public List<Object> saveOrUpdateFMSTxn(FMSTransaction fmsTransaction) throws Exception {

		List<Object> response = fmsTxnPostgresDao.upsert(fmsTransaction);

		// Update Txn-list object in Redis...

		return response;
	}
}
