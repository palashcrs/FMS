package com.get.edgepay.fms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.get.edgepay.fms.exception.CacheException;
import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.exception.DuplicateRecordException;
import com.get.edgepay.fms.facade.FMSFacade;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.service.FMSRuleService;
import com.get.edgepay.fms.util.FMSUtil;

@Service
public class FMSRuleServiceImpl implements FMSRuleService {

	private static final Logger log = LoggerFactory.getLogger(FMSRuleServiceImpl.class);

	@Autowired
	private FMSFacade fmsFacade;

	@Transactional(rollbackFor = { DBException.class, DuplicateRecordException.class, CacheException.class })
	@Override
	public int createRule(List<FMSRule> fmsRules) throws Exception {

		int rowsCreated = 0;

		if (!FMSUtil.getInstance().isAnyObjectNull(fmsFacade)) {
			log.debug("Autowired object(s) null! Exit from method");
			return 0;
		}

		try {
			rowsCreated = fmsFacade.saveRule(fmsRules);

		} catch (RedisConnectionFailureException e) {
			throw new CacheException();
		}

		return rowsCreated;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { DBException.class, CacheException.class })
	@Override
	public List<FMSRule> getAllRuleS() throws Exception {
		
		List<FMSRule> fmsRules = null;
		
		if (!FMSUtil.getInstance().isAnyObjectNull(fmsFacade)) {
			log.debug("Autowired object(s) null! Exit from method");
			return null;
		}
		
		try {
			fmsRules = fmsFacade.getAllRules();

		} catch (RedisConnectionFailureException e) {
			throw new CacheException();
		}
		
		return fmsRules;
	}

}
