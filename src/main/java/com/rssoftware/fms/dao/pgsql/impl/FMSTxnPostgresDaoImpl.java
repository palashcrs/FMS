package com.rssoftware.fms.dao.pgsql.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.rssoftware.fms.common.Postgres;
import com.rssoftware.fms.dao.FMSTxnPostgresDao;
import com.rssoftware.fms.exception.DBException;
import com.rssoftware.fms.model.FMSTransaction;
import com.rssoftware.fms.util.FMSUtil;

@Postgres
public class FMSTxnPostgresDaoImpl implements FMSTxnPostgresDao {

	private static final Logger log = LoggerFactory.getLogger(FMSTxnPostgresDaoImpl.class);

	private static final String UPSERT_SQL = "INSERT INTO EDGEPAY_TRANSACTION(EDGEPAY_TXN_ID,EDGEPAY_TXN_TYPE,EDGEPAY_TXN_TOTAL_AMT,EDGEPAY_TXN_STATUS,EDGEPAY_FMS_TXN_STATUS,EDGEPAY_TXN_EMAIL,EDGEPAY_TXN_CARD_NO,EDGEPAY_TXN_IP,EDGEPAY_TXN_STR_ADDR,EDGEPAY_TXN_CUSTNAME,EDGEPAY_TXN_AVS_ZIP,EDGEPAY_TXN_AVS_STR_ADDR,EDGEPAY_TXN_AVS_CITY,EDGEPAY_TXN_AVS_STATE,EDGEPAY_TXN_GEOLOC_IP,EDGEPAY_TXN_DEVICE_ID,EDGEPAY_TXN_NOTES,EDGEPAY_TXN_CREATED_BY,EDGEPAY_TXN_CREATION_TS,EDGEPAY_TXN_UPDATED_BY,EDGEPAY_TXN_UPDATED_TS)  VALUES(:edgePayTxnId,:txnType,:txnAmt,:edgePayTxnStatus,:fmsTxnStatus,:email,:cardNo,:ip,:strAddr,:custName,:avsZip,:avsStrAddr,:avsCity,:avsState,:geoLoc,:deviceId,:notes,:createdBy,:createdTs,:updatedBy,:updatedTs) "
			+ " ON CONFLICT(EDGEPAY_TXN_ID) DO UPDATE SET EDGEPAY_TXN_STATUS=:edgePayTxnStatus,EDGEPAY_FMS_TXN_STATUS=:fmsTxnStatus,EDGEPAY_TXN_NOTES=:notes,EDGEPAY_TXN_UPDATED_BY=:updatedBy,EDGEPAY_TXN_UPDATED_TS=:updatedTs";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNTemplate;

	@Override
	public List<Object> upsert(FMSTransaction fmsTransaction) throws Exception {

		if (!FMSUtil.getInstance().isAnyObjectNull(jdbcNTemplate)) {
			log.info("Autowired object(s) null! Exit from method");
			return null;
		}

		List<Object> responseList = null;
		Integer fmsTxnId = null;
		Timestamp creationTSRes = null;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Timestamp creationTS = FMSUtil.getInstance().currentTimestamp();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("edgePayTxnId", fmsTransaction.getEdgePayTxnId());
		params.put("txnType", fmsTransaction.getTxnType());
		params.put("txnAmt", fmsTransaction.getTxnTotalAmt());
		params.put("edgePayTxnStatus", fmsTransaction.getEdgePayTxnStatus());
		params.put("fmsTxnStatus", fmsTransaction.getFmsTxnStatus());
		params.put("email", fmsTransaction.getEmail());
		params.put("cardNo", fmsTransaction.getCardNo());
		params.put("ip", fmsTransaction.getIp());
		params.put("strAddr", fmsTransaction.getStrAddr());
		params.put("custName", fmsTransaction.getCustName());
		params.put("avsZip", fmsTransaction.getAvsZip());
		params.put("avsStrAddr", fmsTransaction.getAvsStrAddr());
		params.put("avsCity", fmsTransaction.getAvsCity());
		params.put("avsState", fmsTransaction.getAvsState());
		params.put("geoLoc", fmsTransaction.getGeoIp());
		params.put("deviceId", fmsTransaction.getDeviceId());
		params.put("notes", fmsTransaction.getNotes());
		params.put("createdBy", fmsTransaction.getCreatedBy());
		params.put("createdTs", creationTS);
		params.put("updatedBy", fmsTransaction.getUpdatedBy());
		params.put("updatedTs", creationTS);
		MapSqlParameterSource paramSrc = new MapSqlParameterSource(params);
		try {
			jdbcNTemplate.update(UPSERT_SQL, paramSrc, keyHolder);
			Map<String, Object> allReturnedKeys = keyHolder.getKeys();
			fmsTxnId = (Integer) allReturnedKeys.get("edgepay_fms_txn_id");
			creationTSRes = (Timestamp) allReturnedKeys.get("EDGEPAY_TXN_CREATION_TS");
			responseList = new ArrayList<Object>();
			responseList.add(fmsTxnId);
			responseList.add(creationTSRes);
			responseList.add(creationTS);

		} catch (BadSqlGrammarException ex) {
			log.error("SQL syntax error! Could not insert record", ex);
			throw new DBException();
		}

		return responseList;
	}

}
