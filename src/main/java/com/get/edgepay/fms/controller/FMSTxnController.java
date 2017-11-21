package com.get.edgepay.fms.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.get.edgepay.fms.common.FMSErrorCode;
import com.get.edgepay.fms.constant.FMSConstant;
import com.get.edgepay.fms.constant.FMSResponseStatus;
import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.dto.FMSResponse;
import com.get.edgepay.fms.exception.CacheException;
import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.exception.InputParamNotFoundException;
import com.get.edgepay.fms.exception.RequestNotFoundException;
import com.get.edgepay.fms.model.FMSTransaction;
import com.get.edgepay.fms.service.FMSTransactionService;
import com.get.edgepay.fms.util.FMSCommonUtil;

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
	 * @return fmsResponse
	 */
	@RequestMapping(value = "/fraudcalc", method = RequestMethod.POST)
	public FMSResponse getFmsTypes(@RequestBody FMSRequest fmsRequest) {
		log.info("*********/fmstxn/fraudcalc | FMSRequest : " + fmsRequest);
		FMSResponse fmsResponse = new FMSResponse();
		Object fmsOb = null;
		FMSTransaction fmsTxn = null;

		try {
			FMSCommonUtil.getInstance().validateInput(fmsRequest, FMSConstant.TXN_FRAUD_CALC_FLAG);

			fmsTxn = fmsTransactionService.calculateFraudAndSaveTxn(fmsRequest);
			log.info("FMSTxn : " + fmsTxn);
			fmsOb = fmsTxn;

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
			} else if (e instanceof DBException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.DB_ERROR);
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
