package com.rssoftware.fms.service;

import java.util.concurrent.ExecutionException;

import com.rssoftware.fms.model.FMSTransaction;
import com.rssoftware.fms.vo.FMSRequest;

public interface FMSTransactionService {

	FMSTransaction calculateFraudAndSaveTxn(FMSRequest fmsRequest) throws InterruptedException, ExecutionException, Exception;
}
