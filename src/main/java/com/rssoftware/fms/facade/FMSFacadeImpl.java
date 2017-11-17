package com.rssoftware.fms.facade;

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

@Component
public class FMSFacadeImpl implements FMSFacade {

	private static final Logger log = LoggerFactory.getLogger(FMSFacadeImpl.class);

	@Autowired
	private FMSRuleTypePostgresDao fmsRuleTypePostgresDao;

	@Autowired
	private FMSRulePostgresDao fmsRulePostgresDao;

	@Autowired
	private FMSTxnPostgresDao fmsTxnPostgresDao;

	@Override
	public List<FMSRuleDetails> getPubRuleDetails() {

		List<FMSRuleDetails> fmsPubStatusList = null;

		if (fmsPubStatusList == null) {
			log.info("No data found in Cache, getting Public Rules from DB...");
			fmsPubStatusList = fmsRulePostgresDao.getPubRuleDetails();
		}

		return fmsPubStatusList;
	}

	@Override
	public List<FMSRuleDetails> getPriRuleDetails() {

		List<FMSRuleDetails> priRuleDetails = null;

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

		return response;
	}

	@Override
	public List<FMSRuleType> fetchAllRuleTypes() throws Exception {

		List<FMSRuleType> allRuleTypeList = null;

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
