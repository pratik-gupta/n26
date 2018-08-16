package com.n26.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.validation.constraints.NotNull;

/**
 * Pojo class to map the transactions properties.
 * @author Pratik
 *
 */
public class Transactions implements Serializable {

	@NotNull
	private BigDecimal amount;
	@NotNull
	private Instant timestamp;

	public BigDecimal getAmount() {
		return amount;
	}

	public Transactions setAmount(BigDecimal amount) {
		this.amount = amount;
		return this;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public Transactions setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
		return this;
	}
}
