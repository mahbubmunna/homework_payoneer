package com.mahbub.payoneercheckout.model;

import lombok.Data;

public @Data class Payment{
	private String reference;
	private int amount;
	private String currency;
}