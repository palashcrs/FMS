package com.rssoftware.fms.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rssoftware.fms.common.FMSErrorCode;
import com.rssoftware.fms.constant.FMSConstant;
import com.rssoftware.fms.constant.FMSResponseStatus;
import com.rssoftware.fms.exception.CacheException;
import com.rssoftware.fms.exception.DuplicateRecordException;
import com.rssoftware.fms.exception.InputParamNotFoundException;
import com.rssoftware.fms.exception.RequestNotFoundException;
import com.rssoftware.fms.model.FMSTransaction;
import com.rssoftware.fms.service.FMSTransactionService;
import com.rssoftware.fms.util.FMSCommonUtil;
import com.rssoftware.fms.vo.FMSRequest;
import com.rssoftware.fms.vo.FMSResponse;

/**
 * @author PalashC
 * @since 2017-11-03
 */
@RestController
@RequestMapping("/fmstxn")
public class FMSTxnController {

	private static final Logger log = LoggerFactory.getLogger(FMSTxnController.class);

	@Autowired
	private FMSTransactionService fmsTransactionService;

	/**
	 * REST API to detect fraud in a transaction
	 * @param fmsRequest
	 * @return fmsRequest
	 */
	@RequestMapping(value = "/fraudcalc", method = RequestMethod.POST)
	public FMSResponse getFmsTypes(@RequestBody FMSRequest fmsRequest) {
		log.info("*********/fmstxn/fraudcalc | FMSRequest : " + fmsRequest);
		FMSResponse fmsResponse = new FMSResponse();
		Object fmsOb = null;
		String fmsTxnStatus = null;

		try {
			FMSCommonUtil.getInstance().validateInput(fmsRequest, FMSConstant.TXN_FRAUD_CALC_FLAG);

			fmsTxnStatus = fmsTransactionService.calculateFraud(fmsRequest);
			log.info("FMSTxnStatus : " + fmsTxnStatus);

			FMSTransaction fmsTransaction = fmsRequest.getFmsTransactions().get(0);
			fmsTransaction.setFmsTxnStatus(fmsTxnStatus);
			fmsOb = fmsTransaction;

			fmsResponse.setFmsResponseCode(FMSResponseStatus.SUCCESS.toString());

		} catch (Exception e) {
			fmsResponse.setFmsResponseCode(FMSResponseStatus.FAILURE.toString());
			if (e instanceof RequestNotFoundException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.REQ_NOT_FOUND);
			} else if (e instanceof InputParamNotFoundException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.INPUT_PARAM_NOT_FOUND);
			} else if (e instanceof InterruptedException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.TIMEOUT_ERROR);
			} else if (e instanceof ExecutionException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.EXECUTE_ERROR);
			} else if (e instanceof DuplicateRecordException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.DUPLICATE_RECORD);
			} else if (e instanceof CacheException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.CACHE_ERROR);
			}
			log.error(e.getMessage(), e);
		} finally {
			fmsResponse.setFmsResult(fmsOb);
		}

		return fmsResponse;
	}

}
