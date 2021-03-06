package com.mahbub.payoneercheckout.model;

import lombok.Data;

public @Data class ListResult{
	private String resultCode;
	private Networks networks;
	private String resultInfo;
	private ReturnCode returnCode;
	private Identification identification;
	private String integrationType;
	private Interaction interaction;
	private Links links;
	private String operationType;
	private Style style;
	private Payment payment;
	private String operation;
	private String timestamp;
	private Status status;
}