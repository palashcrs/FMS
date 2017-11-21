package com.get.edgepay.fms.dao.pgsql.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.get.edgepay.fms.common.Postgres;
import com.get.edgepay.fms.dao.FMSRulePostgresDao;
import com.get.edgepay.fms.model.FMSRule;

@Postgres
public class FMSRulePostgresDaoImpl implements FMSRulePostgresDao {

	private static final Logger log = LoggerFactory.getLogger(FMSRulePostgresDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNTemplate;

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
