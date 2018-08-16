package com.n26.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.n26.model.Statistics;
import com.n26.model.Transactions;
import com.n26.validation.factories.RequestValidatorFactory;
/**
 * Service to hand transactions realted business logic.
 * @author Pratik
 *
 */
@Service
public class TransactionService {

	@Autowired
	RequestValidatorFactory RequestValidatorFactory;
	
	@Value("${statistic.interval.time}")
	private long statisticInterval;
	
	List<Transactions> tranactionsList = new Vector<>();

	public ResponseEntity createTransaction(Transactions transactions) {
		RequestValidatorFactory.createTransactionValidator(transactions, statisticInterval).validate();
		tranactionsList.add(transactions);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Statistics> calculateStatistics(){
		List<Transactions> statistincsTranactionsList = new ArrayList<>();
		synchronized (tranactionsList) {
			statistincsTranactionsList = tranactionsList.stream()
					.filter(trx -> (Instant.now().getEpochSecond()- trx.getTimestamp().getEpochSecond()) < statisticInterval)
					.collect(Collectors.toList());
		}
		return new ResponseEntity<Statistics>(getFinalStatistics(statistincsTranactionsList), HttpStatus.OK);
	}

	Statistics getFinalStatistics(List<Transactions> statistincsTranactionsList) {
		
		BigDecimal sum = BigDecimal.ZERO, avg = BigDecimal.ZERO, max = BigDecimal.ZERO, min = BigDecimal.ZERO;
		long count = statistincsTranactionsList.size();
		
		for(Transactions transactions: statistincsTranactionsList) {
			BigDecimal amount = transactions.getAmount();
			if(sum.equals(BigDecimal.ZERO)) min = amount;
			sum = sum.add(amount);
			max = amount.max(max);
			min = amount.min(min);
		}
		avg = count > 0 ? sum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP): BigDecimal.ZERO;
		avg = avg.setScale(2 ,BigDecimal.ROUND_HALF_UP);
		return new Statistics()
				.setSum(sum.setScale(2 ,BigDecimal.ROUND_HALF_UP))
				.setAvg(avg)
				.setMax(max.setScale(2 ,BigDecimal.ROUND_HALF_UP))
				.setMin(min.setScale(2 ,BigDecimal.ROUND_HALF_UP))
				.setCount(count);
	}

	public ResponseEntity deleteTransactions() {
		synchronized (tranactionsList) {
			tranactionsList.clear();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
