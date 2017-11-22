package com.get.edgepay.fms.dao.pgsql.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.get.edgepay.fms.common.Postgres;
import com.get.edgepay.fms.dao.FMSRulePostgresDao;
import com.get.edgepay.fms.exception.DBException;
import com.get.edgepay.fms.exception.DuplicateRecordException;
import com.get.edgepay.fms.exception.RecordFetchException;
import com.get.edgepay.fms.model.FMSRule;
import com.get.edgepay.fms.model.FMSRuleType;
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

	private static final String SELECT_RULES_SQL = "SELECT a.*,b.* FROM EDGEPAY_FMS_RULE_TYPE a JOIN EDGEPAY_FMS_RULE b "
			 + "ON a.EDGEPAY_RULE_TYPE_ID=b.EDGEPAY_RULE_TYPE_ID "
			 + "WHERE a.EDGEPAY_ACCESS_MODE=";
	
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
	public List<FMSRule> getPubRules() throws Exception {
		
		if (!FMSUtil.getInstance().isAnyObjectNull(jdbcTemplate)) {
			log.info("Autowired object(s) null! Exit from method");
			return null;
		}
		
		StringBuffer pubSql = new StringBuffer(SELECT_RULES_SQL);
		pubSql.append("'PUBLIC'");
		
		List<FMSRule> pubRules = null;
		try {
			pubRules = jdbcTemplate.query(pubSql.toString(), new RowMapper<FMSRule>() {
				@Override
				public FMSRule mapRow(ResultSet rs, int rowNum) throws SQLException {
					FMSRule fmsRule = new FMSRule();
					FMSRuleType fmsRuleType = new FMSRuleType();
					fmsRuleType.setRuleTypeId(rs.getInt("EDGEPAY_RULE_TYPE_id"));
					fmsRuleType.setRuleType(rs.getString("EDGEPAY_RULE_TYPE"));
					fmsRuleType.setAccessMode(rs.getString("EDGEPAY_ACCESS_MODE"));
					fmsRuleType.setAction(rs.getString("EDGEPAY_ACTION"));
					fmsRule.setFmsRuleType(fmsRuleType);
					fmsRule.setRuleId(rs.getInt("EDGEPAY_RULE_ID"));
					fmsRule.setEmail(rs.getString("EDGEPAY_EMAIL"));
					fmsRule.setCardNo(rs.getString("EDGEPAY_CARD_NO"));
					fmsRule.setIp(rs.getString("EDGEPAY_IP"));
					fmsRule.setStrAddr(rs.getString("EDGEPAY_STR_ADDR"));
					fmsRule.setWord(rs.getString("EDGEPAY_WORD"));
					fmsRule.setCardLimit(rs.getInt("EDGEPAY_LIMIT_CARD"));
					fmsRule.setIpLimit(rs.getInt("EDGEPAY_LIMIT_IP"));
					if ("".equals(rs.getString("EDGEPAY_LIMIT_MAX_AMT")) || rs.getString("EDGEPAY_LIMIT_MAX_AMT") != null) {
						fmsRule.setMaxAmtLimit(new BigDecimal(rs.getString("EDGEPAY_LIMIT_MAX_AMT")));
					}
					fmsRule.setTimePeriod(rs.getString("EDGEPAY_TIME_PERIOD"));
					fmsRule.setAvsZip(rs.getString("EDGEPAY_AVS_ZIP"));
					fmsRule.setAvsStrAddr(rs.getString("EDGEPAY_AVS_STR_ADDR"));
					fmsRule.setAvsCity(rs.getString("EDGEPAY_AVS_CITY"));
					fmsRule.setAvsState(rs.getString("EDGEPAY_AVS_STATE"));
					fmsRule.setAvsResult(rs.getString("EDGEPAY_AVS_RESULT"));
					fmsRule.setGeoIp(rs.getString("EDGEPAY_GEOLOC_IP"));
					fmsRule.setDeviceId(rs.getString("EDGEPAY_DEVICE_ID"));
					fmsRule.setCreatedBy(rs.getString("EDGEPAY_RULE_CREATED_BY"));
					fmsRule.setCreationTs(rs.getTimestamp("EDGEPAY_RULE_CREATION_TS"));
					
					return fmsRule;
				}
			});
		} catch (BadSqlGrammarException ex) {
			log.error("SQL syntax error! Could not insert record", ex);
			throw new DBException();
		}

		return pubRules;
	}

	@Override
	public List<FMSRule> getPriRules() throws Exception {
		if (!FMSUtil.getInstance().isAnyObjectNull(jdbcTemplate)) {
			log.info("Autowired object(s) null! Exit from method");
			return null;
		}
		
		StringBuffer priSql = new StringBuffer(SELECT_RULES_SQL);
		priSql.append("'PRIVATE'");
		
		List<FMSRule> priRules = null;
		try {
			priRules = jdbcTemplate.query(priSql.toString(), new RowMapper<FMSRule>() {
				@Override
				public FMSRule mapRow(ResultSet rs, int rowNum) throws SQLException {
					FMSRule fmsRule = new FMSRule();
					FMSRuleType fmsRuleType = new FMSRuleType();
					fmsRuleType.setRuleTypeId(rs.getInt("EDGEPAY_RULE_TYPE_id"));
					fmsRuleType.setRuleType(rs.getString("EDGEPAY_RULE_TYPE"));
					fmsRuleType.setAccessMode(rs.getString("EDGEPAY_ACCESS_MODE"));
					fmsRuleType.setAction(rs.getString("EDGEPAY_ACTION"));
					fmsRule.setFmsRuleType(fmsRuleType);
					fmsRule.setRuleId(rs.getInt("EDGEPAY_RULE_ID"));
					fmsRule.setEmail(rs.getString("EDGEPAY_EMAIL"));
					fmsRule.setCardNo(rs.getString("EDGEPAY_CARD_NO"));
					fmsRule.setIp(rs.getString("EDGEPAY_IP"));
					fmsRule.setStrAddr(rs.getString("EDGEPAY_STR_ADDR"));
					fmsRule.setWord(rs.getString("EDGEPAY_WORD"));
					fmsRule.setCardLimit(rs.getInt("EDGEPAY_LIMIT_CARD"));
					fmsRule.setIpLimit(rs.getInt("EDGEPAY_LIMIT_IP"));
					if ("".equals(rs.getString("EDGEPAY_LIMIT_MAX_AMT")) || rs.getString("EDGEPAY_LIMIT_MAX_AMT") != null) {
						fmsRule.setMaxAmtLimit(new BigDecimal(rs.getString("EDGEPAY_LIMIT_MAX_AMT")));
					}
					fmsRule.setTimePeriod(rs.getString("EDGEPAY_TIME_PERIOD"));
					fmsRule.setAvsZip(rs.getString("EDGEPAY_AVS_ZIP"));
					fmsRule.setAvsStrAddr(rs.getString("EDGEPAY_AVS_STR_ADDR"));
					fmsRule.setAvsCity(rs.getString("EDGEPAY_AVS_CITY"));
					fmsRule.setAvsState(rs.getString("EDGEPAY_AVS_STATE"));
					fmsRule.setAvsResult(rs.getString("EDGEPAY_AVS_RESULT"));
					fmsRule.setGeoIp(rs.getString("EDGEPAY_GEOLOC_IP"));
					fmsRule.setDeviceId(rs.getString("EDGEPAY_DEVICE_ID"));
					fmsRule.setCreatedBy(rs.getString("EDGEPAY_RULE_CREATED_BY"));
					fmsRule.setCreationTs(rs.getTimestamp("EDGEPAY_RULE_CREATION_TS"));
					
					return fmsRule;
				}
			});
		} catch (BadSqlGrammarException ex) {
			log.error("SQL syntax error! Could not insert record", ex);
			throw new DBException();
		}

		return priRules;
	}

}
