package com.get.edgepay.fms.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.util.FMSCommonUtil;

/**
 * @author PalashC
 * @since 2017-11-03
 */
public class FMSRuleConfiguration {

	private static final Logger log = LoggerFactory.getLogger(FMSRuleConfiguration.class);

	public FMSRuleConfiguration() {

	}

	/**
	 * This method validates all Public and Private Rules.
	 */
	public static Map<Integer, String> triggerRule(List<FMSRule> rules, FMSRequest fmsRequest) {
		Map<Integer, String> fmsTriggeredRuleResponse = new HashMap<>();
		String fmsTriggeredRuleStatus = null;
		String emailReq = null;
		String cardNoReq = null;
		String custName = null;

		if (fmsRequest != null) {
			if (fmsRequest.getFmsTransactions() != null) {
				if (fmsRequest.getFmsTransactions().get(0) != null) {
					emailReq = fmsRequest.getFmsTransactions().get(0).getEmail();
					cardNoReq = fmsRequest.getFmsTransactions().get(0).getCardNo();
					custName = fmsRequest.getFmsTransactions().get(0).getCustName();
				}
			}
		}

		if (rules != null) {
			for (FMSRule r : rules) {
				if (r != null) {
					if(r.getFmsRuleType() != null){
						// ******************Trigger RuleType = EMAIL**********//
						if (FMSRuleConstant.RULETYPE_EMAIL.getRuleTypeValue().equalsIgnoreCase(r.getFmsRuleType().getRuleType())) {
							if (emailReq != null || "".equals(emailReq)) {
								fmsTriggeredRuleStatus = triggerEmailRule(r.getEmail(), emailReq, r.getFmsRuleType().getAction());
								if (fmsTriggeredRuleStatus != null) { // consider only 'R' and 'D'
									fmsTriggeredRuleResponse.put(r.getRuleId(), fmsTriggeredRuleStatus);
								}
							}
						}
						// ******************Trigger RuleType = CARDNO**********//
						if (FMSRuleConstant.RULETYPE_CARDNO.getRuleTypeValue().equalsIgnoreCase(r.getFmsRuleType().getRuleType())) {
							if (cardNoReq != null || "".equals(cardNoReq)) {
								fmsTriggeredRuleStatus = triggerCardNoRule(r.getCardNo(), cardNoReq, r.getFmsRuleType().getAction());
								if (fmsTriggeredRuleStatus != null) {
									fmsTriggeredRuleResponse.put(r.getRuleId(), fmsTriggeredRuleStatus);
								}
							}
						}
						// ******************Trigger RuleType = WORD**********//
						if (FMSRuleConstant.RULETYPE_WORD.getRuleTypeValue().equalsIgnoreCase(r.getFmsRuleType().getRuleType())) {
							if (custName != null || "".equals(custName)) {
								fmsTriggeredRuleStatus = triggerWordRule(r.getWord(), custName,  r.getFmsRuleType().getAction());
								if (fmsTriggeredRuleStatus != null) {
									fmsTriggeredRuleResponse.put(r.getRuleId(), fmsTriggeredRuleStatus);
								}
							}
						}
					}
				}
			}
		}

		return fmsTriggeredRuleResponse;
	}

	private static String triggerEmailRule(String emailRule, String emailReq, String action) {
		String fmsStatus = FMSCommonUtil.getInstance().isEqualAndSetStatus(emailRule, emailReq, action);
		System.out.println("Email rule status : " + fmsStatus);
		return fmsStatus;
	}

	private static String triggerCardNoRule(String cardNoRule, String cardNoReq, String action) {
		String fmsStatus = FMSCommonUtil.getInstance().isEqualAndSetStatus(cardNoRule, cardNoReq, action);
		System.out.println("CardNo rule status : " + fmsStatus);
		return fmsStatus;
	}

	private static String triggerWordRule(String wordRule, String custName, String action) {
		String fmsStatus = FMSCommonUtil.getInstance().isContainsAndSetStatus(wordRule, custName, action);
		System.out.println("Word rule status : " + fmsStatus);
		return fmsStatus;
	}

}
