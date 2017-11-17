package com.get.edgepay.fms.dto;

import java.io.Serializable;

import com.get.edgepay.fms.common.FMSError;

public class FMSResponse implements Serializable {

	private final static long serialVersionUID = 1L;

	private Object fmsResult;

	private String fmsResponseCode;

	private FMSError fmsError;

	public FMSResponse() {

	}

	public Object getFmsResult() {
		return fmsResult;
	}

	public void setFmsResult(Object fmsResult) {
		this.fmsResult = fmsResult;
	}

	public String getFmsResponseCode() {
		return fmsResponseCode;
	}

	public void setFmsResponseCode(String fmsResponseCode) {
		this.fmsResponseCode = fmsResponseCode;
	}

	public FMSError getFmsError() {
		return fmsError;
	}

	public void setFmsError(FMSError fmsError) {
		this.fmsError = fmsError;
	}

	@Override
	public String toString() {
		return "FMSResponse [fmsResult=" + fmsResult + ", fmsResponseCode=" + fmsResponseCode + ", fmsError=" + fmsError
				+ "]";
	}
}
