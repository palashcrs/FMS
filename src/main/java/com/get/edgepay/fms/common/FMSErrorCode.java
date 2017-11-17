
package com.get.edgepay.fms.common;

public enum FMSErrorCode {

	REQ_NOT_FOUND("ERR00000"),
	INPUT_PARAM_NOT_FOUND("ERR00001"),
	RECORD_FETCH_ERROR("ERR00002"),
	DUPLICATE_RECORD("ERR00003"),
	CACHE_ERROR("ERR00004"),
	TIMEOUT_ERROR("ERR00005"),
	EXECUTE_ERROR("ERR00006"),
	DB_ERROR("ERR00007");

	private String errorCode;

	private FMSErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}