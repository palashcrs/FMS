package com.rssoftware.fms.common;

import java.util.ArrayList;
import java.util.List;

import com.rssoftware.fms.model.FMSRuleDetails;
import com.rssoftware.fms.vo.FMSRequest;

/**
 * @author PalashC
 * @since 2017-11-03
 */
public class FMSRuleConfiguration {

	public FMSRuleConfiguration() {

	}

	/**
	 * This method validates all Public and Private Rules.
	 */
	public static List<String> triggerRule(List<FMSRuleDetails> ruleDetails, FMSRequest fmsRequest) {
		List<String> fmsStatus = new ArrayList<>();
		String emailReq = null;
		String cardNoReq = null;
		String wordReq = null;

		if (fmsRequest != null) {
			emailReq = fmsRequest.getEmail();
			cardNoReq = fmsRequest.getCardNo();
			wordReq = fmsRequest.getWord();
		}

		if (ruleDetails != null) {
			if (emailReq != null || "".equals(emailReq)) {
				fmsStatus.add(triggerEmailRule(ruleDetails, emailReq));
			}
			if (cardNoReq != null || "".equals(cardNoReq)) {
				fmsStatus.add(triggerCardNoRule(ruleDetails, cardNoReq));
			}
			if (wordReq != null || "".equals(wordReq)) {
				fmsStatus.add(triggerWordRule(ruleDetails, wordReq));
			}
		}

		return fmsStatus;
	}

	private static String triggerEmailRule(List<FMSRuleDetails> ruleDetails, String email) {
		String fmsStatus = null;
		for (FMSRuleDetails rd : ruleDetails) {
			if (email.equals(rd.getEmail())) {
				if ("Review".equalsIgnoreCase(rd.getAction())) {
					fmsStatus = "R";
				} else if ("Decline".equalsIgnoreCase(rd.getAction())) {
					fmsStatus = "D";
				}
			}
		}
		System.out.println("Email rule fmsStatus = " + fmsStatus);

		return fmsStatus;
	}

	private static String triggerCardNoRule(List<FMSRuleDetails> ruleDetails, String cardNo) {
		String fmsStatus = null;
		for (FMSRuleDetails rd : ruleDetails) {
			if (cardNo.equals(rd.getCardNo())) {
				if ("Review".equalsIgnoreCase(rd.getAction())) {
					fmsStatus = "R";
				} else if ("Decline".equalsIgnoreCase(rd.getAction())) {
					fmsStatus = "D";
				}
			}
		}
		System.out.println("CardNo rule fmsStatus = " + fmsStatus);

		return fmsStatus;
	}

	private static String triggerWordRule(List<FMSRuleDetails> ruleDetails, String word) {
		String fmsStatus = null;
		for (FMSRuleDetails rd : ruleDetails) {
			if (rd != null && rd.getWord() != null) {
				if (rd.getWord().contains(word)) {
					if ("Review".equalsIgnoreCase(rd.getAction())) {
						fmsStatus = "R";
					} else if ("Decline".equalsIgnoreCase(rd.getAction())) {
						fmsStatus = "D";
					}
				}
			}
		}
		System.out.println("Word rule fmsStatus = " + fmsStatus);

		return fmsStatus;
	}

}
