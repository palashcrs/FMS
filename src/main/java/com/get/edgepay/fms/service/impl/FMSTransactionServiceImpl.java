package com.get.edgepay.fms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.get.edgepay.fms.common.FMSRuleConfiguration;
import com.get.edgepay.fms.common.FMSRuleConstant;
import com.get.edgepay.fms.constant.FMSTxnStatusConstant;
import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.exception.CacheException;
import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.facade.FMSFacade;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSTransaction;
import com.get.edgepay.fms.service.FMSTransactionService;
import com.get.edgepay.fms.util.FMSCommonUtil;
import com.get.edgepay.fms.util.FMSUtil;

@Service
public class FMSTransactionServiceImpl implements FMSTransactionService {

	private static final Logger log = LoggerFactory.getLogger(FMSTransactionServiceImpl.class);

	@Autowired
	private FMSFacade fmsFacade;

	@Transactional(rollbackFor = { DBException.class, InterruptedException.class, ExecutionException.class,
			CacheException.class })
	@Override
	public FMSTransaction calculateFraudAndSaveTxn(FMSRequest fmsRequest) throws Exception {
		FMSTransaction fmsTxn = null;
		String fmsTxnStatus = null;
		Map<Integer, String> fmsTriggeredPubRulesRes = null;
		Map<Integer, String> fmsTriggeredPriRulesRes = null;
		Map<Integer, String> fmsTriggeredRulesRes = null;
		Set<Integer> fmsRulesViolated = null;
		List<String> fmsMergedStatusList = null;
		List<Object> facadeResponse = null;
		ExecutorService execotorService = Executors.newFixedThreadPool(2);

		// ******Fetch all Public-Rules and trigger them in different thread:
		if (fmsRequest != null) {
			if (!execotorService.isShutdown()) {
				Future<Map<Integer, String>> future = execotorService.submit(new Callable<Map<Integer, String>>() {
					Map<Integer, String> pubRulesTriggeredRes;

					@Override
					public Map<Integer, String> call() throws Exception {
						List<FMSRule> pubRuleDetails = fmsFacade.getPubRuleDetails();
						if (pubRuleDetails != null) {
							pubRulesTriggeredRes = FMSRuleConfiguration.triggerRule(pubRuleDetails, fmsRequest);
						}
						return pubRulesTriggeredRes;
					}
				});

				fmsTriggeredPubRulesRes = future.get();
			}
		}

		// ******Fetch all Private-Rules and trigger them in different thread:
		if (fmsRequest != null) {
			if (!execotorService.isShutdown()) {
				Future<Map<Integer, String>> future = execotorService.submit(new Callable<Map<Integer, String>>() {
					Map<Integer, String> priRulesTriggeredRes;

					@Override
					public Map<Integer, String> call() throws Exception {
						List<FMSRule> priRuleDetails = fmsFacade.getPriRuleDetails();
						if (priRuleDetails != null) {
							priRulesTriggeredRes = FMSRuleConfiguration.triggerRule(priRuleDetails, fmsRequest);
						}
						return priRulesTriggeredRes;
					}
				});

				fmsTriggeredPriRulesRes = future.get();
			}
		}

		execotorService.shutdown();

		if (execotorService.isShutdown()) {
			fmsTriggeredRulesRes = FMSUtil.getInstance().mergeMaps(fmsTriggeredPubRulesRes, fmsTriggeredPriRulesRes);

			if (fmsTriggeredRulesRes != null && fmsTriggeredRulesRes.size() > 0) {
				fmsRulesViolated = fmsTriggeredRulesRes.keySet();
				log.info("FMSRulesViolated set : " + fmsRulesViolated.toString());

				fmsMergedStatusList = new ArrayList<>();
				for (Integer key : fmsRulesViolated) {
					fmsMergedStatusList.add(fmsTriggeredRulesRes.get(key));
				}
				log.info("FMSMergedStatusList : " + fmsMergedStatusList);
			}

			fmsTxnStatus = calculateFMSTxnStatus(fmsMergedStatusList);

			if (fmsRequest != null) {
				if (fmsRequest.getFmsTransactions() != null) {
					fmsTxn = fmsRequest.getFmsTransactions().get(0);
					if (fmsTxn != null) {
						fmsTxn.setFmsTxnStatus(fmsTxnStatus);
						if (fmsRulesViolated != null) {
							fmsTxn.setViolatedRules(fmsRulesViolated.toString());
						}
						facadeResponse = fmsFacade.saveOrUpdateFMSTxn(fmsTxn);
					}
				}
			}

			if (facadeResponse != null) {
				fmsTxn.setFmsTxnId(FMSCommonUtil.getInstance().generateFMSTxnId((String) facadeResponse.get(0)));
				fmsTxn.setCreationTs((Timestamp) facadeResponse.get(1));
				fmsTxn.setUpdateTs((Timestamp) facadeResponse.get(2));
			}
		}

		return fmsTxn;
	}

	private String calculateFMSTxnStatus(List<String> mergedList) {
		String fmsTxnStatus = null;

		if (mergedList != null) {
			if (mergedList.contains(FMSRuleConstant.RULETYPE_ACTION_D.getRuleTypeValue())) {
				fmsTxnStatus = FMSTxnStatusConstant.DECLINE.toString();
			} else {
				int mergedListSize = mergedList.size();
				int occurrencesOfReview = Collections.frequency(mergedList, FMSRuleConstant.RULETYPE_ACTION_R.getRuleTypeValue());
				int occurrencesOfOthers = mergedListSize - occurrencesOfReview;
				if (occurrencesOfReview > occurrencesOfOthers) {
					fmsTxnStatus = FMSTxnStatusConstant.REVIEW.toString();
				} else {
					fmsTxnStatus = FMSTxnStatusConstant.APPROVED.toString();
				}
			}
		} else {
			fmsTxnStatus = FMSTxnStatusConstant.APPROVED.toString();
		}

		return fmsTxnStatus;
	}

}
