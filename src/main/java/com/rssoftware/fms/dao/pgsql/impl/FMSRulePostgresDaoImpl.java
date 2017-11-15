package com.rssoftware.fms.dao.pgsql.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.rssoftware.fms.common.Postgres;
import com.rssoftware.fms.dao.FMSRulePostgresDao;
import com.rssoftware.fms.model.FMSRuleDetails;

@Postgres
public class FMSRulePostgresDaoImpl implements FMSRulePostgresDao {

	private static final Logger log = LoggerFactory.getLogger(FMSRulePostgresDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNTemplate;

	@Override
	public List<FMSRuleDetails> getPubRuleDetails() {
		FMSMockData fmsMockData = new FMSMockData();
		List<FMSRuleDetails> pubRuleDetails = fmsMockData.getMockPublicRuleDetails();
		return pubRuleDetails;
	}

	@Override
	public List<FMSRuleDetails> getPriRuleDetails() {
		FMSMockData fmsMockData = new FMSMockData();
		List<FMSRuleDetails> priRuleDetails = fmsMockData.getMockPrivateRuleDetails();
		return priRuleDetails;
	}

}
