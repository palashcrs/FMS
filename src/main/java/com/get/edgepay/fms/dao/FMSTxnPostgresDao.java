package com.get.edgepay.fms.dao;

import java.util.List;

import com.get.edgepay.fms.model.FMSTransaction;

public interface FMSTxnPostgresDao {

	List<Object> upsert(FMSTransaction fmsTransaction) throws Exception;
}
