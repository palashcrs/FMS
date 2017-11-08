package com.rssoftware.fms.service;

import java.util.concurrent.ExecutionException;

import com.rssoftware.fms.vo.FMSRequest;

public interface FMSTransactionService {

	String calculateFraud(FMSRequest fmsRequest) throws InterruptedException, ExecutionException;
}
