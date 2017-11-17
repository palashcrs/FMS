package com.get.edgepay.fms.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.get.edgepay.fms.model.FMSRuleDetails;
import com.get.edgepay.fms.util.FMSCommonUtil;
import com.get.edgepay.fms.vo.FMSRequest;

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
	public static List<String> triggerRule(List<FMSRuleDetails> ruleDetails, FMSRequest fmsRequest) {
		List<String> fmsStatus = new ArrayList<>();
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

		if (ruleDetails != null) {
			for (FMSRuleDetails rd : ruleDetails) {
				if (rd != null) {
					// ******************Trigger RuleType = EMAIL**********//
					if (FMSRuleDetailsConstant.RULETYPE_EMAIL.getRuleTypeValue().equalsIgnoreCase(rd.getRuleType())) {
						if (emailReq != null || "".equals(emailReq)) {
							fmsStatus.add(triggerEmailRule(rd.getEmail(), emailReq, rd.getAction()));
						}
					}
					// ******************Trigger RuleType = CARDNO**********//
					if (FMSRuleDetailsConstant.RULETYPE_CARDNO.getRuleTypeValue().equalsIgnoreCase(rd.getRuleType())) {
						if (cardNoReq != null || "".equals(cardNoReq)) {
							fmsStatus.add(triggerCardNoRule(rd.getCardNo(), cardNoReq, rd.getAction()));
						}
					}
					// ******************Trigger RuleType = WORD**********//
					if (FMSRuleDetailsConstant.RULETYPE_WORD.getRuleTypeValue().equalsIgnoreCase(rd.getRuleType())) {
						if (custName != null || "".equals(custName)) {
							fmsStatus.add(triggerWordRule(rd.getWord(), custName, rd.getAction()));
						}
					}
				}
			}
		}

		return fmsStatus;
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
