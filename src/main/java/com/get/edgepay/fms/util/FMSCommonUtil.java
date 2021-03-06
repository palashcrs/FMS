package com.get.edgepay.fms.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.get.edgepay.fms.common.FMSError;
import com.get.edgepay.fms.common.FMSErrorCode;
import com.get.edgepay.fms.common.FMSErrorCodeMap;
import com.get.edgepay.fms.common.FMSRuleConstant;
import com.get.edgepay.fms.constant.FMSConstant;
import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.dto.FMSResponse;
import com.get.edgepay.fms.exception.InputParamNotFoundException;
import com.get.edgepay.fms.exception.RequestNotFoundException;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;

public class FMSCommonUtil {

	private static final Logger log = LoggerFactory.getLogger(FMSCommonUtil.class);

	private static FMSCommonUtil fmsCommonUtil = new FMSCommonUtil();

	private FMSCommonUtil() {

	}

	public static FMSCommonUtil getInstance() {
		return fmsCommonUtil;
	}

	public final void addError(FMSResponse fmsResponse, FMSErrorCode fmsErrorCode) {
		FMSError fmsError = new FMSError();
		fmsError.setFmsErrorCode(fmsErrorCode.getErrorCode());
		fmsError.setFmsErrorMessage(FMSErrorCodeMap.getMsg(fmsErrorCode.getErrorCode()));
		fmsResponse.setFmsError(fmsError);
	}

	public void validateInput(Object obj, String checkFlag) throws Exception {
		if (obj == null) {
			throw new RequestNotFoundException();
		}

		if (obj instanceof FMSRequest) {
			FMSRequest fmsRequest = (FMSRequest) obj;

			if (FMSConstant.NEW_RULE_FLAG.equals(checkFlag)) {
				List<FMSRule> fmsRules = fmsRequest.getFmsRules();
				if (fmsRules == null) {
					throw new InputParamNotFoundException();
				}
				for (FMSRule fmsRule : fmsRules) {
					if (fmsRule != null) {
						if (fmsRule.getFmsRuleType() == null) {
							throw new InputParamNotFoundException();
						}
					}
				}
			}

			if (FMSConstant.NEW_RULETYPE_FLAG.equals(checkFlag)) {
				FMSRuleType fmsRuleType = fmsRequest.getFmsRuleType();
				if (fmsRuleType == null) {
					throw new InputParamNotFoundException();
				}
				if (fmsRuleType.getRuleType() == null || "".equals(fmsRuleType.getRuleType())
						|| fmsRuleType.getAccessMode() == null || "".equals(fmsRuleType.getAccessMode())
						|| fmsRuleType.getAction() == null || "".equals(fmsRuleType.getAction())) {
					throw new InputParamNotFoundException();
				}
			}

			if (FMSConstant.TXN_FRAUD_CALC_FLAG.equals(checkFlag)) {
				if (fmsRequest.getMerchantId() == null || fmsRequest.getFmsTransactions() == null) {
					throw new InputParamNotFoundException();
				}
				if (fmsRequest.getFmsTransactions() != null) {
					if (fmsRequest.getFmsTransactions().get(0) == null) {
						throw new InputParamNotFoundException();
					}
				}
				if (fmsRequest.getFmsTransactions() != null) {
					if (fmsRequest.getFmsTransactions().get(0) != null) {
						if (fmsRequest.getFmsTransactions().get(0).getEdgePayTxnId() == null
								|| "".equals(fmsRequest.getFmsTransactions().get(0).getEdgePayTxnId())) {
							throw new InputParamNotFoundException();
						}
					}
				}
			}
		}
	}

	public String generateFMSTxnId(String id) {
		String fmsTxnId = "NA";
		if (fmsTxnId != null || !("".equals(fmsTxnId))) {
			fmsTxnId = "FMSTXN" + id;
		}

		return fmsTxnId;
	}

	public String isEqualAndSetStatus(String arg1, String arg2, String action) {
		String fmsStatus = null;
		if (arg1 != null) {
			if (arg1.equals(arg2)) {
				if (FMSRuleConstant.RULETYPE_ACCESSMODE_REVIEW.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleConstant.RULETYPE_ACTION_R.getRuleTypeValue();
				} else if (FMSRuleConstant.RULETYPE_ACCESSMODE_DECLINE.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleConstant.RULETYPE_ACTION_D.getRuleTypeValue();
				}
			}
		}

		return fmsStatus;
	}

	public String isContainsAndSetStatus(String arg1, String arg2, String action) {
		String fmsStatus = null;
		if (arg2 != null) {
			if (arg2.contains(arg1)) {
				if (FMSRuleConstant.RULETYPE_ACCESSMODE_REVIEW.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleConstant.RULETYPE_ACTION_R.getRuleTypeValue();
				} else if (FMSRuleConstant.RULETYPE_ACCESSMODE_DECLINE.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleConstant.RULETYPE_ACTION_D.getRuleTypeValue();
				}
			}
		}

		return fmsStatus;
	}

}
