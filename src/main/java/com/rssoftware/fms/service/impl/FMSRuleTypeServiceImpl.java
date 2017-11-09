package com.rssoftware.fms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rssoftware.fms.exception.CacheException;
import com.rssoftware.fms.exception.DuplicateRecordException;
import com.rssoftware.fms.exception.RecordFetchException;
import com.rssoftware.fms.facade.FMSFacade;
import com.rssoftware.fms.model.FMSRuleType;
import com.rssoftware.fms.service.FMSRuleTypeService;
import com.rssoftware.fms.util.FMSUtil;

@Service
public class FMSRuleTypeServiceImpl implements FMSRuleTypeService {

	private static final Logger log = LoggerFactory.getLogger(FMSRuleTypeServiceImpl.class);

	@Autowired
	private FMSFacade fmsFacade;

	@Transactional(rollbackFor = { DuplicateRecordException.class, CacheException.class })
	@Override
	public int createNewRuleType(FMSRuleType fmsRuleType) throws Exception {

		int rowsCreated = 0;

		if (!FMSUtil.getInstance().isAnyObjectNull(fmsFacade)) {
			log.debug("Autowired object(s) null! Exit from method");
			return 0;
		}

		try {
			rowsCreated = fmsFacade.saveRuleType(fmsRuleType);

		} catch (RedisConnectionFailureException e) {
			throw new CacheException();
		}

		return rowsCreated;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { RecordFetchException.class, CacheException.class })
	@Override
	public List<FMSRuleType> getAllRuleTypes() throws Exception {

		List<FMSRuleType> allRuleTypeList = null;

		if (!FMSUtil.getInstance().isAnyObjectNull(fmsFacade)) {
			log.debug("Autowired object(s) null! Exit from method");
			return null;
		}

		try {
			allRuleTypeList = fmsFacade.fetchAllRuleTypes();

		} catch (RedisConnectionFailureException e) {
			throw new CacheException();
		}

		return allRuleTypeList;
	}
}
