package com.mahbub.payoneercheckout.model;

import lombok.Data;

public @Data class Identification{
	private String shortId;
	private String longId;
	private String transactionId;
}