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
import com.get.edgepay.fms.exception.CacheException;
import com.get.edgepay.fms.exception.DuplicateRecordException;
import com.get.edgepay.fms.exception.InputParamNotFoundException;
import com.get.edgepay.fms.exception.RecordFetchException;
import com.get.edgepay.fms.exception.RequestNotFoundException;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.service.FMSRuleTypeService;
import com.get.edgepay.fms.util.FMSCommonUtil;
import com.get.edgepay.fms.vo.FMSRequest;
import com.get.edgepay.fms.vo.FMSResponse;

/**
 * @author PalashC
 * @since 2017-11-03
 */
@RestController
@RequestMapping("/fmsruletype")
public class FMSRuleTypeController {

	private static final Logger log = LoggerFactory.getLogger(FMSRuleTypeController.class);

	@Autowired
	private FMSRuleTypeService fmsRuleTypeService;

	/**
	 * REST API to create a new RuleType
	 * @param fmsRequest
	 * @return fmsRequest
	 */
	@RequestMapping(value = "/newruletype", method = RequestMethod.POST)
	public FMSResponse addNewRuleType(@RequestBody FMSRequest fmsRequest) {
		log.info("*********/fmsruletype/newruletype | FMSRequest : " + fmsRequest);
		FMSResponse fmsResponse = new FMSResponse();
		Object fmsOb = null;

		try {
			FMSCommonUtil.getInstance().validateInput(fmsRequest, FMSConstant.NEW_RULETYPE_FLAG);

			int recordsCreated = fmsRuleTypeService.createNewRuleType(fmsRequest.getFmsRuleType());
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
	 * REST API to fetch all RuleTypes
	 * @param fmsRequest
	 * @return fmsRequest
	 */
	@RequestMapping(value = "/allruletypes", method = RequestMethod.POST)
	public FMSResponse getAllRuleType(@RequestBody FMSRequest fmsRequest) {
		log.info("*********/fmsruletype/allruletypes | FMSRequest : " + fmsRequest);
		FMSResponse fmsResponse = new FMSResponse();
		List<FMSRuleType> allRuleTypeList = null;

		try {
			allRuleTypeList = fmsRuleTypeService.getAllRuleTypes();
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
			fmsResponse.setFmsResult(allRuleTypeList);
		}

		return fmsResponse;
	}

}
