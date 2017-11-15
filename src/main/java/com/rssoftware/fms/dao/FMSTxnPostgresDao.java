package com.rssoftware.fms.dao;

import java.util.List;

import com.rssoftware.fms.model.FMSTransaction;

public interface FMSTxnPostgresDao {

	List<Object> upsert(FMSTransaction fmsTransaction) throws Exception;
}
