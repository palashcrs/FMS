package com.get.edgepay.fms.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.get.edgepay.fms.dao.pgsql.impl.FMSMockData;
import com.get.edgepay.fms.dto.FMSRequest;

public class FMSTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FMSMockData fmsMockData = new FMSMockData();
		//FMSRequest fmsRequest = fmsMockData.getMockTransaction();
		ExecutorService execotorService = Executors.newFixedThreadPool(2);

		// **********************Get data and Triggering Rules:
	}
}
