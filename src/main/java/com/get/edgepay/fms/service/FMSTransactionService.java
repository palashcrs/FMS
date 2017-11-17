package com.get.edgepay.fms.service;

import java.util.concurrent.ExecutionException;

import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.model.FMSTransaction;

public interface FMSTransactionService {

	FMSTransaction calculateFraudAndSaveTxn(FMSRequest fmsRequest) throws InterruptedException, ExecutionException, Exception;
}
