package com.rssoftware.fms.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rssoftware.fms.dao.FMSRuleTypePostgresDao;
import com.rssoftware.fms.dao.FMSTxnPostgresDao;
import com.rssoftware.fms.model.FMSRuleDetails;
import com.rssoftware.fms.model.FMSRuleType;
import com.rssoftware.fms.repository.CacheRepository;

@Component
public class FMSFacadeImpl implements FMSFacade {

	private static final Logger log = LoggerFactory.getLogger(FMSFacadeImpl.class);

	@Autowired
	private FMSRuleTypePostgresDao fmsRuleTypePostgresDao;

	@Autowired
	private FMSTxnPostgresDao fmsTxnPostgresDao;

	@Autowired
	private CacheRepository cacheRepository;

	@Override
	public List<FMSRuleDetails> getPubRuleDetails() {

		List<FMSRuleDetails> fmsPubStatusList = null;

		// List<FMSRuleDetails> fmsPubStatusList = (List<FMSRuleDetails>) cacheRepository.findByKey("PUB-RULES");

		if (fmsPubStatusList == null) {
			fmsPubStatusList = fmsTxnPostgresDao.getPubRuleDetails();
		}

		return fmsPubStatusList;
	}

	@Override
	public List<FMSRuleDetails> getPriRuleDetails() {

		List<FMSRuleDetails> priRuleDetails = null;

		// List<FMSRuleDetails> priRuleDetails = (List<FMSRuleDetails>) cacheRepository.findByKey("PRI-RULES");

		if (priRuleDetails == null) {
			priRuleDetails = fmsTxnPostgresDao.getPriRuleDetails();
		}

		return priRuleDetails;
	}

	@Override
	public int saveRuleType(FMSRuleType fmsRuleType) throws Exception {

		int response = fmsRuleTypePostgresDao.insert(fmsRuleType);

		// Update RuleType-list object in Redis...

		return response;
	}

	@Override
	public List<FMSRuleType> fetchAllRuleTypes() throws Exception {

		List<FMSRuleType> allRuleTypeList = null;

		// List<FMSRuleType> allRuleTypeList = cacheRepository.findByKey("RT-ALL");

		if (allRuleTypeList == null) {
			allRuleTypeList = fmsRuleTypePostgresDao.getAll();
		}

		return allRuleTypeList;
	}
}
