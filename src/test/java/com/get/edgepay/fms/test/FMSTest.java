package com.get.edgepay.fms.test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.get.edgepay.fms.common.FMSRuleConfiguration;
import com.get.edgepay.fms.dao.pgsql.impl.FMSMockData;
import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.model.FMSRuleDetails;
import com.get.edgepay.fms.util.FMSUtil;

public class FMSTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FMSMockData fmsMockData = new FMSMockData();
		FMSRequest fmsRequest = fmsMockData.getMockTransaction();
		ExecutorService execotorService = Executors.newFixedThreadPool(2);

		// **********************Get data and Triggering Rules:

		List<String> fmsPubStatus = null;
		List<String> fmsPriStatus = null;

		if (fmsRequest != null) {
			if (!execotorService.isShutdown()) {
				Future<List<String>> future = execotorService.submit(new Callable<List<String>>() {
					List<String> pubStatusList;

					@Override
					public List<String> call() throws Exception {
						List<FMSRuleDetails> pubRuleDetails = fmsMockData.getMockPublicRuleDetails();
						if (pubRuleDetails != null) {
							pubStatusList = FMSRuleConfiguration.triggerRule(pubRuleDetails, fmsRequest);
							System.out.println("***********FMSPubStatus : " + pubStatusList);
						}
						return pubStatusList;
					}
				});

				fmsPubStatus = future.get();
			}
		}

		if (fmsRequest != null) {
			if (!execotorService.isShutdown()) {
				Future<List<String>> future = execotorService.submit(new Callable<List<String>>() {
					List<String> priStatusList;

					@Override
					public List<String> call() throws Exception {
						List<FMSRuleDetails> priRuleDetails = fmsMockData.getMockPrivateRuleDetails();
						if (priRuleDetails != null) {
							priStatusList = FMSRuleConfiguration.triggerRule(priRuleDetails, fmsRequest);
							System.out.println("***********FMSPriStatus : " + priStatusList);
						}
						return priStatusList;
					}
				});

				fmsPriStatus = future.get();
			}
		}

		execotorService.shutdown();

		if (execotorService.isShutdown()) {
			System.out.println("FMSPubStatus : " + fmsPubStatus + " FMSPubStatus : " + fmsPriStatus);

			List<String> fmsMergedStatusList = FMSUtil.getInstance().mergeLists(fmsPubStatus, fmsPriStatus);
			System.out.println("FMSMergedStatus : " + fmsMergedStatusList);

			int occurrences = Collections.frequency(fmsMergedStatusList, "R");
			System.out.println("No of R status : " + occurrences);
			System.out.println("total size : " + fmsMergedStatusList.size());
		}
	}
}
