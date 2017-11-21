package com.get.edgepay.fms.dao.pgsql.impl;

import java.util.ArrayList;
import java.util.List;

import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;

public class FMSMockData {

	public List<FMSRule> getMockPublicRuleDetails() {
		List<FMSRule> publicRules = new ArrayList<>();

		FMSRule fmsRule = new FMSRule();
		FMSRuleType fmsRuleType = new FMSRuleType();
		fmsRuleType.setRuleTypeId(1);
		fmsRuleType.setRuleType("EMAIL");
		fmsRuleType.setAccessMode("PUBLIC");
		fmsRuleType.setAction("Review");
		fmsRule.setRuleId(36);
		fmsRule.setFmsRuleType(fmsRuleType);
		fmsRule.setEmail("abc@gmail.com");
		
		FMSRule fmsRule2 = new FMSRule();
		FMSRuleType fmsRuleType2 = new FMSRuleType();
		fmsRuleType2.setRuleTypeId(2);
		fmsRuleType2.setRuleType("EMAIL");
		fmsRuleType2.setAccessMode("PUBLIC");
		fmsRuleType2.setAction("Decline");
		fmsRule2.setRuleId(5);
		fmsRule2.setFmsRuleType(fmsRuleType2);
		fmsRule2.setEmail("pqr@gmail.com");
		
		FMSRule fmsRule3 = new FMSRule();
		FMSRuleType fmsRuleType3 = new FMSRuleType();
		fmsRuleType3.setRuleTypeId(5);
		fmsRuleType3.setRuleType("CARDNO");
		fmsRuleType3.setAccessMode("PUBLIC");
		fmsRuleType3.setAction("Review");
		fmsRule3.setRuleId(3);
		fmsRule3.setFmsRuleType(fmsRuleType3);
		fmsRule3.setCardNo("442256342313");
		
		FMSRule fmsRule4 = new FMSRule();
		FMSRuleType fmsRuleType4 = new FMSRuleType();
		fmsRuleType4.setRuleTypeId(6);
		fmsRuleType4.setRuleType("CARDNO");
		fmsRuleType4.setAccessMode("PUBLIC");
		fmsRuleType4.setAction("Decline");
		fmsRule4.setRuleId(4);
		fmsRule4.setFmsRuleType(fmsRuleType4);
		fmsRule4.setCardNo("225856431209");
				
		publicRules.add(fmsRule);
		publicRules.add(fmsRule2);
		publicRules.add(fmsRule3);
		publicRules.add(fmsRule4);

		return publicRules;
	}

	public List<FMSRule> getMockPrivateRuleDetails() {
		List<FMSRule> privateRules = new ArrayList<>();

		FMSRule fmsRule = new FMSRule();
		FMSRuleType fmsRuleType = new FMSRuleType();
		fmsRuleType.setRuleTypeId(3);
		fmsRuleType.setRuleType("EMAIL");
		fmsRuleType.setAccessMode("PRIVATE");
		fmsRuleType.setAction("Review");
		fmsRule.setRuleId(39);
		fmsRule.setFmsRuleType(fmsRuleType);
		fmsRule.setEmail("zzz@cc.co.in");

		FMSRule fmsRule2 = new FMSRule();
		FMSRuleType fmsRuleType2 = new FMSRuleType();
		fmsRuleType2.setRuleTypeId(4);
		fmsRuleType2.setRuleType("EMAIL");
		fmsRuleType2.setAccessMode("PRIVATE");
		fmsRuleType2.setAction("Decline");
		fmsRule2.setRuleId(87);
		fmsRule2.setFmsRuleType(fmsRuleType2);
		fmsRule2.setEmail("pal@rs.com");

		FMSRule fmsRule3 = new FMSRule();
		FMSRuleType fmsRuleType3 = new FMSRuleType();
		fmsRuleType3.setRuleTypeId(7);
		fmsRuleType3.setRuleType("CARDNO");
		fmsRuleType3.setAccessMode("PRIVATE");
		fmsRuleType3.setAction("Review");
		fmsRule3.setRuleId(3);
		fmsRule3.setFmsRuleType(fmsRuleType3);
		fmsRule3.setCardNo("550078123454");
		
		FMSRule fmsRule4 = new FMSRule();
		FMSRuleType fmsRuleType4 = new FMSRuleType();
		fmsRuleType4.setRuleTypeId(8);
		fmsRuleType4.setRuleType("CARDNO");
		fmsRuleType4.setAccessMode("PRIVATE");
		fmsRuleType4.setAction("Decline");
		fmsRule4.setRuleId(7);
		fmsRule4.setFmsRuleType(fmsRuleType4);
		fmsRule4.setCardNo("123456780657");
		
		FMSRule fmsRule5 = new FMSRule();
		FMSRuleType fmsRuleType5 = new FMSRuleType();
		fmsRuleType5.setRuleTypeId(9);
		fmsRuleType5.setRuleType("WORD");
		fmsRuleType5.setAccessMode("PRIVATE");
		fmsRuleType5.setAction("Review");
		fmsRule5.setRuleId(109);
		fmsRule5.setFmsRuleType(fmsRuleType5);
		fmsRule5.setWord("aaa");
		
		FMSRule fmsRule6 = new FMSRule();
		FMSRuleType fmsRuleType6 = new FMSRuleType();
		fmsRuleType6.setRuleTypeId(9);
		fmsRuleType6.setRuleType("WORD");
		fmsRuleType6.setAccessMode("PRIVATE");
		fmsRuleType6.setAction("Decline");
		fmsRule6.setRuleId(44);
		fmsRule6.setFmsRuleType(fmsRuleType6);
		fmsRule6.setWord("bbb");

		privateRules.add(fmsRule);
		privateRules.add(fmsRule2);
		privateRules.add(fmsRule3);
		privateRules.add(fmsRule4);

		privateRules.add(fmsRule5);
		privateRules.add(fmsRule6);

		return privateRules;
	}

	/*	
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
	*/
}
