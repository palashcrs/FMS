package com.rssoftware.fms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rssoftware.fms.common.FMSError;
import com.rssoftware.fms.common.FMSErrorCode;
import com.rssoftware.fms.common.FMSErrorCodeMap;
import com.rssoftware.fms.common.FMSRuleDetailsConstant;
import com.rssoftware.fms.constant.FMSConstant;
import com.rssoftware.fms.exception.InputParamNotFoundException;
import com.rssoftware.fms.exception.RequestNotFoundException;
import com.rssoftware.fms.model.FMSRuleType;
import com.rssoftware.fms.vo.FMSRequest;
import com.rssoftware.fms.vo.FMSResponse;

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
						if (fmsRequest.getFmsTransactions().get(0).getEdgePayTxnId() == null) {
							throw new InputParamNotFoundException();
						}
					}
				}
			}
		}
	}

	public String isEqualAndSetStatus(String arg1, String arg2, String action) {
		String fmsStatus = null;
		if (arg1 != null) {
			if (arg1.equals(arg2)) {
				if (FMSRuleDetailsConstant.RULETYPE_ACCESSMODE_REVIEW.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleDetailsConstant.RULETYPE_ACTION_R.getRuleTypeValue();
				} else if (FMSRuleDetailsConstant.RULETYPE_ACCESSMODE_DECLINE.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleDetailsConstant.RULETYPE_ACTION_D.getRuleTypeValue();
				}
			}
		}

		return fmsStatus;
	}

	public String isContainsAndSetStatus(String arg1, String arg2, String action) {
		String fmsStatus = null;
		if (arg1 != null) {
			if (arg1.contains(arg2)) {
				if (FMSRuleDetailsConstant.RULETYPE_ACCESSMODE_REVIEW.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleDetailsConstant.RULETYPE_ACTION_R.getRuleTypeValue();
				} else if (FMSRuleDetailsConstant.RULETYPE_ACCESSMODE_DECLINE.getRuleTypeValue().equalsIgnoreCase(action)) {
					fmsStatus = FMSRuleDetailsConstant.RULETYPE_ACTION_D.getRuleTypeValue();
				}
			}
		}

		return fmsStatus;
	}

}
