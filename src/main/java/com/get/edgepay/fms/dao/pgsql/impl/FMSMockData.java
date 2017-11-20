package com.get.edgepay.fms.dao.pgsql.impl;

import java.util.ArrayList;
import java.util.List;

import com.get.edgepay.fms.dto.FMSRequest;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleDetails;

public class FMSMockData {

	public List<FMSRuleDetails> getMockPublicRuleDetails() {
		List<FMSRuleDetails> publicRules = new ArrayList<>();

		FMSRuleDetails fmsRuleDetails = new FMSRuleDetails();
		fmsRuleDetails.setRuleId(1);
		fmsRuleDetails.setRuleType("EMAIL");
		fmsRuleDetails.setAccessMode("PUBLIC");
		fmsRuleDetails.setAction("Review");
		fmsRuleDetails.setEmail("abc@gmail.com");

		FMSRuleDetails fmsRuleDetails2 = new FMSRuleDetails();
		fmsRuleDetails2.setRuleId(5);
		fmsRuleDetails2.setRuleType("Email");
		fmsRuleDetails2.setAccessMode("PUBLIC");
		fmsRuleDetails2.setAction("Decline");
		fmsRuleDetails2.setEmail("pqr@gmail.com");

		FMSRuleDetails fmsRuleDetails3 = new FMSRuleDetails();
		fmsRuleDetails3.setRuleId(2);
		fmsRuleDetails3.setRuleType("CARDNO");
		fmsRuleDetails3.setAccessMode("PUBLIC");
		fmsRuleDetails3.setAction("Review");
		fmsRuleDetails3.setCardNo("442256342313");

		FMSRuleDetails fmsRuleDetails4 = new FMSRuleDetails();
		fmsRuleDetails4.setRuleId(3);
		fmsRuleDetails4.setRuleType("CARDNO");
		fmsRuleDetails4.setAccessMode("PUBLIC");
		fmsRuleDetails4.setAction("Decline");
		fmsRuleDetails4.setCardNo("225856431209");

		publicRules.add(fmsRuleDetails);
		publicRules.add(fmsRuleDetails2);
		publicRules.add(fmsRuleDetails3);
		publicRules.add(fmsRuleDetails4);

		return publicRules;
	}

	public List<FMSRuleDetails> getMockPrivateRuleDetails() {
		List<FMSRuleDetails> privateRules = new ArrayList<>();

		FMSRuleDetails fmsRuleDetails = new FMSRuleDetails();
		fmsRuleDetails.setRuleId(4);
		fmsRuleDetails.setRuleType("EMAIL");
		fmsRuleDetails.setAccessMode("PRIVATE");
		fmsRuleDetails.setAction("Review");
		fmsRuleDetails.setEmail("zzz@cc.co.in");

		FMSRuleDetails fmsRuleDetails2 = new FMSRuleDetails();
		fmsRuleDetails2.setRuleId(6);
		fmsRuleDetails2.setRuleType("EMAIL");
		fmsRuleDetails2.setAccessMode("PRIVATE");
		fmsRuleDetails2.setAction("Decline");
		fmsRuleDetails2.setEmail("pal@rs.com");

		FMSRuleDetails fmsRuleDetails3 = new FMSRuleDetails();
		fmsRuleDetails3.setRuleId(11);
		fmsRuleDetails3.setRuleType("CARDNO");
		fmsRuleDetails3.setAccessMode("PRIVATE");
		fmsRuleDetails3.setAction("Review");
		fmsRuleDetails3.setCardNo("550078123454");

		FMSRuleDetails fmsRuleDetails4 = new FMSRuleDetails();
		fmsRuleDetails4.setRuleId(53);
		fmsRuleDetails4.setRuleType("CARDNO");
		fmsRuleDetails4.setAccessMode("PRIVATE");
		fmsRuleDetails4.setAction("Decline");
		fmsRuleDetails4.setCardNo("123456780657");

		FMSRuleDetails fmsRuleDetails5 = new FMSRuleDetails();
		fmsRuleDetails5.setRuleId(27);
		fmsRuleDetails5.setRuleType("WORD");
		fmsRuleDetails5.setAccessMode("PRIVATE");
		fmsRuleDetails5.setAction("Review");
		fmsRuleDetails5.setWord("aaa");

		FMSRuleDetails fmsRuleDetails6 = new FMSRuleDetails();
		fmsRuleDetails6.setRuleId(74);
		fmsRuleDetails6.setRuleType("WORD");
		fmsRuleDetails6.setAccessMode("PRIVATE");
		fmsRuleDetails6.setAction("Decline");
		fmsRuleDetails6.setWord("bbb");

		privateRules.add(fmsRuleDetails);
		privateRules.add(fmsRuleDetails2);
		privateRules.add(fmsRuleDetails3);
		privateRules.add(fmsRuleDetails4);

		privateRules.add(fmsRuleDetails5);
		privateRules.add(fmsRuleDetails6);

		return privateRules;
	}

	public FMSRequest getMockTransaction() {
		FMSRequest fmsRequest = new FMSRequest();
		fmsRequest.setMerchantId("MID55893266");
		List<FMSRule> fmsRules = new ArrayList<>();
		FMSRule fmsRule = new FMSRule();
		fmsRule.setEmail("pqr@gmail.com");
		fmsRule.setCardNo("442256342313");
		fmsRule.setWord("aaaa");
		fmsRules.set(0, fmsRule);

		return fmsRequest;
	}
}
