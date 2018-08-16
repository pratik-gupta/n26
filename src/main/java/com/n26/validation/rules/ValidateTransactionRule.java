package com.n26.validation.rules;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.n26.exception.InvalidParameterException;
import com.n26.model.Transactions;

public class ValidateTransactionRule implements ValidationRule {

	private Transactions transactions;
	private long statisticInterval;

	public ValidateTransactionRule(Transactions transactions, long statisticInterval) {
		this.transactions = transactions;
		this.statisticInterval = statisticInterval;
	}

	@Override
	public void apply() {
		isTimestampValid(transactions.getTimestamp());
	}

	private void isTimestampValid(Instant timestamp) {
		Long timeVariation = Instant.now().getEpochSecond() - timestamp.getEpochSecond();
		if (timeVariation >= statisticInterval) {
			throw new InvalidParameterException(HttpStatus.NO_CONTENT, "Transaction should be older than 60 sec.");
		} else if (timeVariation < 0) {
			throw new InvalidParameterException(HttpStatus.UNPROCESSABLE_ENTITY, "Transaction should not be in future");
		}
	}
}
