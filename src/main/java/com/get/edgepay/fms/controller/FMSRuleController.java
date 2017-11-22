package com.get.edgepay.fms.controller;

import java.util.List;

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
import com.get.edgepay.fms.exception.DuplicateRecordException;
import com.get.edgepay.fms.exception.InputParamNotFoundException;
import com.get.edgepay.fms.exception.RecordFetchException;
import com.get.edgepay.fms.exception.RequestNotFoundException;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.service.FMSRuleService;
import com.get.edgepay.fms.util.FMSCommonUtil;

/**
 * @author PalashC
 * @since 2017-11-21
 */
@RestController
@RequestMapping("/fmsrule")
public class FMSRuleController {

	private static final Logger log = LoggerFactory.getLogger(FMSRuleController.class);

	@Autowired
	private FMSRuleService fmsRuleService;

	/**
	 * REST API to create new Rule
	 * @param fmsRequest
	 * @return fmsResponse
	 */
	@RequestMapping(value = "/newrule", method = RequestMethod.POST)
	public FMSResponse addRule(@RequestBody FMSRequest fmsRequest) {
		log.info("*********/fmsrule/newrule | FMSRequest : " + fmsRequest);
		FMSResponse fmsResponse = new FMSResponse();
		Object fmsOb = null;

		try {
			FMSCommonUtil.getInstance().validateInput(fmsRequest, FMSConstant.NEW_RULE_FLAG);
			
			int recordsCreated = fmsRuleService.createRule(fmsRequest.getFmsRules());
			if (recordsCreated > 0) {
				fmsResponse.setFmsResponseCode(FMSResponseStatus.SUCCESS.toString());
			}

		} catch (Exception e) {
			fmsResponse.setFmsResponseCode(FMSResponseStatus.FAILURE.toString());
			log.info("Exception occurs : " + e);
			if (e instanceof RequestNotFoundException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.REQ_NOT_FOUND);
			} else if (e instanceof InputParamNotFoundException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.INPUT_PARAM_NOT_FOUND);
			} else if (e instanceof DBException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.DB_ERROR);
			} else if (e instanceof DuplicateRecordException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.DUPLICATE_RECORD);
			} else if (e instanceof CacheException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.CACHE_ERROR);
			}
		} finally {
			fmsResponse.setFmsResult(fmsOb);
		}

		return fmsResponse;

	}
	
	/**
	 * REST API to fetch all Rules
	 * @param fmsRequest
	 * @return fmsResponse
	 */
	@RequestMapping(value = "/allrules", method = RequestMethod.POST)
	public FMSResponse getAllRules(@RequestBody FMSRequest fmsRequest) {
		log.info("*********/fmsrule/allrules | FMSRequest : " + fmsRequest);
		FMSResponse fmsResponse = new FMSResponse();
		List<FMSRule> allRuleS = null;
		
		try {
			allRuleS = fmsRuleService.getAllRuleS();
			fmsResponse.setFmsResponseCode(FMSResponseStatus.SUCCESS.toString());

		} catch (Exception e) {
			fmsResponse.setFmsResponseCode(FMSResponseStatus.FAILURE.toString());
			log.info("Exception occurs : " + e);
			if (e instanceof RecordFetchException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.RECORD_FETCH_ERROR);
			} else if (e instanceof CacheException) {
				FMSCommonUtil.getInstance().addError(fmsResponse, FMSErrorCode.CACHE_ERROR);
			}
		} finally {
			fmsResponse.setFmsResult(allRuleS);
		}

		
		return fmsResponse;
		
	}

}
