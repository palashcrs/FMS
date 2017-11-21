package com.get.edgepay.fms.dao.pgsql.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.get.edgepay.fms.common.Postgres;
import com.get.edgepay.fms.dao.FMSRulePostgresDao;
import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.exception.DuplicateRecordException;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.util.FMSUtil;

@Postgres
public class FMSRulePostgresDaoImpl implements FMSRulePostgresDao {

	private static final Logger log = LoggerFactory.getLogger(FMSRulePostgresDaoImpl.class);

	private static final String INSERT_SQL = "INSERT INTO EDGEPAY_FMS_RULE "
			 + " ( "
			 + "EDGEPAY_RULE_TYPE_ID, "
			 + "EDGEPAY_EMAIL, "
			 + "EDGEPAY_CARD_NO, "
			 + "EDGEPAY_IP, "
			 + "EDGEPAY_STR_ADDR, "
			 + "EDGEPAY_WORD, "
			 + "EDGEPAY_LIMIT_CARD, "
			 + "EDGEPAY_LIMIT_IP, "
			 + "EDGEPAY_LIMIT_MAX_AMT, "
			 + "EDGEPAY_TIME_PERIOD, "
			 + "EDGEPAY_AVS_ZIP, "
			 + "EDGEPAY_AVS_STR_ADDR, "
			 + "EDGEPAY_AVS_CITY, "
			 + "EDGEPAY_AVS_STATE, "
			 + "EDGEPAY_AVS_RESULT, "
			 + "EDGEPAY_GEOLOC_IP, "
			 + "EDGEPAY_DEVICE_ID, "
			 + "EDGEPAY_RULE_CREATED_BY, "
			 + "EDGEPAY_RULE_CREATION_TS"
			 + ") "
			 + " VALUES"
			 + "("
			 + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
			 + ") ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNTemplate;

	@Override
	public int insert(List<FMSRule> fmsRules) throws Exception {

		if (!FMSUtil.getInstance().isAnyObjectNull(jdbcNTemplate)) {
			log.info("Autowired object(s) null! Exit from method");
			return 0;
		}

		if (fmsRules == null || fmsRules.size() == 0) {
			return 0;
		}

		int[] insertResult = {};
		List<Object[]> inputList = new ArrayList<Object[]>();
		Timestamp ruleCreationTS = FMSUtil.getInstance().currentTimestamp();
		for (FMSRule fmsRule : fmsRules) {
			if (fmsRule != null) {
				if (fmsRule.getFmsRuleType() != null) {
					Object[] tmpOb = { 
									   fmsRule.getFmsRuleType().getRuleTypeId(), fmsRule.getEmail(),
									   fmsRule.getCardNo(), fmsRule.getIp(), fmsRule.getStrAddr(), fmsRule.getWord(),
									   fmsRule.getCardLimit(), fmsRule.getIpLimit(), fmsRule.getMaxAmtLimit(), fmsRule.getTimePeriod(),
									   fmsRule.getAvsZip(), fmsRule.getAvsStrAddr(), fmsRule.getAvsCity(), fmsRule.getAvsState(),
									   fmsRule.getAvsResult(), fmsRule.getGeoIp(), fmsRule.getDeviceId(), fmsRule.getCreatedBy(), ruleCreationTS
									 };
					inputList.add(tmpOb);
				}
			}
		}
		try {
			insertResult = jdbcTemplate.batchUpdate(INSERT_SQL, inputList);
		} catch (BadSqlGrammarException ex) {
			log.error("SQL syntax error! Could not insert record", ex);
			throw new DBException();
		} catch (DuplicateKeyException ex) {
			log.error("Duplicate Key! Could not insert record");
			throw new DuplicateRecordException();
		}
		int rowsCreated = insertResult.length;

		return rowsCreated;
	}

	@Override
	public List<FMSRule> getPubRuleDetails() {
		FMSMockData fmsMockData = new FMSMockData();
		List<FMSRule> pubRuleDetails = fmsMockData.getMockPublicRuleDetails();
		return pubRuleDetails;
	}

	@Override
	public List<FMSRule> getPriRuleDetails() {
		FMSMockData fmsMockData = new FMSMockData();
		List<FMSRule> priRuleDetails = fmsMockData.getMockPrivateRuleDetails();
		return priRuleDetails;
	}

}
