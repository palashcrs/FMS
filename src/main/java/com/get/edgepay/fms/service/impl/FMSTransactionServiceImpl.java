package com.get.edgepay.fms.service.impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
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
import com.get.edgepay.fms.common.FMSRuleDetailsConstant;
import com.get.edgepay.fms.constant.FMSTxnStatusConstant;
import com.get.edgepay.fms.exception.CacheException;
import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.facade.FMSFacade;
import com.get.edgepay.fms.model.FMSRuleDetails;
import com.get.edgepay.fms.model.FMSTransaction;
import com.get.edgepay.fms.service.FMSTransactionService;
import com.get.edgepay.fms.util.FMSCommonUtil;
import com.get.edgepay.fms.util.FMSUtil;
import com.get.edgepay.fms.vo.FMSRequest;

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
		List<String> fmsPubStatusList = null;
		List<String> fmsPriStatusList = null;
		List<String> fmsMergedStatusList = null;
		List<Object> facadeResponse = null;
		ExecutorService execotorService = Executors.newFixedThreadPool(2);

		// ******Fetch all Public-Rules and trigger them in different thread:
		if (fmsRequest != null) {
			if (!execotorService.isShutdown()) {
				Future<List<String>> future = execotorService.submit(new Callable<List<String>>() {
					List<String> pubStatusList;

					@Override
					public List<String> call() throws Exception {
						List<FMSRuleDetails> pubRuleDetails = fmsFacade.getPubRuleDetails();
						if (pubRuleDetails != null) {
							pubStatusList = FMSRuleConfiguration.triggerRule(pubRuleDetails, fmsRequest);
						}
						return pubStatusList;
					}
				});

				fmsPubStatusList = future.get();
			}
		}

		// ******Fetch all Private-Rules and trigger them in different thread:
		if (fmsRequest != null) {
			if (!execotorService.isShutdown()) {
				Future<List<String>> future = execotorService.submit(new Callable<List<String>>() {
					List<String> priStatusList;

					@Override
					public List<String> call() throws Exception {
						List<FMSRuleDetails> priRuleDetails = fmsFacade.getPriRuleDetails();
						if (priRuleDetails != null) {
							priStatusList = FMSRuleConfiguration.triggerRule(priRuleDetails, fmsRequest);
						}
						return priStatusList;
					}
				});

				fmsPriStatusList = future.get();
			}
		}

		execotorService.shutdown();

		if (execotorService.isShutdown()) {
			fmsMergedStatusList = FMSUtil.getInstance().mergeLists(fmsPubStatusList, fmsPriStatusList);
			log.info("FMSMergedStatusList : " + fmsMergedStatusList);

			fmsTxnStatus = calculateFMSTxnStatus(fmsMergedStatusList);

			if (fmsRequest != null) {
				if (fmsRequest.getFmsTransactions() != null) {
					fmsTxn = fmsRequest.getFmsTransactions().get(0);
					if (fmsTxn != null) {
						fmsTxn.setFmsTxnStatus(fmsTxnStatus);
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
			if (mergedList.contains(FMSRuleDetailsConstant.RULETYPE_ACTION_D.getRuleTypeValue())) {
				fmsTxnStatus = FMSTxnStatusConstant.DECLINE.toString();
			} else {
				int mergedListSize = mergedList.size();
				int occurrencesOfReview = Collections.frequency(mergedList,
						FMSRuleDetailsConstant.RULETYPE_ACTION_R.getRuleTypeValue());
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
