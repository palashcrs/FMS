package com.get.edgepay.fms.dao.pgsql.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.get.edgepay.fms.common.Postgres;
import com.get.edgepay.fms.dao.FMSRuleTypePostgresDao;
import com.get.edgepay.fms.exception.DuplicateRecordException;
import com.get.edgepay.fms.exception.RecordFetchException;
import com.get.edgepay.fms.model.FMSRuleType;
import com.get.edgepay.fms.util.FMSUtil;

@Postgres
public class FMSRuleTypePostgresDaoImpl implements FMSRuleTypePostgresDao {

	private static final Logger log = LoggerFactory.getLogger(FMSRuleTypePostgresDaoImpl.class);

	private static final String INSERT_SQL = "INSERT INTO EDGEPAY_RULE_TYPE(EDGEPAY_RULE_TYPE,EDGEPAY_ACCESS_MODE,EDGEPAY_ACTION) VALUES(:ruleType,:accessMode,:action)";

	private static final String SELECT_ALL_SQL = "SELECT * FROM EDGEPAY_RULE_TYPE";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNTemplate;

	@Override
	public int insert(FMSRuleType fmsRuleType) throws Exception {

		if (!FMSUtil.getInstance().isAnyObjectNull(jdbcNTemplate)) {
			log.info("Autowired object(s) null! Exit from method");
			return 0;
		}

		int rowsCreated = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ruleType", fmsRuleType.getRuleType());
		params.put("accessMode", fmsRuleType.getAccessMode());
		params.put("action", fmsRuleType.getAction());
		try {
			rowsCreated = jdbcNTemplate.update(INSERT_SQL, params);
		} catch (DuplicateKeyException ex) {
			log.error("Duplicate Key! Could not insert record");
			throw new DuplicateRecordException();
		}

		return rowsCreated;
	}

	@Override
	public List<FMSRuleType> getAll() throws Exception {

		if (!FMSUtil.getInstance().isAnyObjectNull(jdbcTemplate, jdbcNTemplate)) {
			log.info("Autowired object(s) null! Exit from method");
			return null;
		}

		List<FMSRuleType> allRuleTypeList = null;
		try {
			allRuleTypeList = jdbcTemplate.query(SELECT_ALL_SQL, new RowMapper<FMSRuleType>() {
				@Override
				public FMSRuleType mapRow(ResultSet rs, int rowNum) throws SQLException {
					FMSRuleType ruleType = new FMSRuleType();
					ruleType.setRuleTypeId(rs.getInt("EDGEPAY_RULE_TYPE_id"));
					ruleType.setRuleType(rs.getString("EDGEPAY_RULE_TYPE"));
					ruleType.setAccessMode(rs.getString("EDGEPAY_ACCESS_MODE"));
					ruleType.setAction(rs.getString("EDGEPAY_ACTION"));
					return ruleType;
				}
			});
		} catch (Exception ex) {
			log.error("Failed to fetch from DB " + ex.getMessage(), ex);
			throw new RecordFetchException();
		}

		return allRuleTypeList;
	}
}
