package com.n26.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.n26.BaseTestConfig;
import com.n26.model.Statistics;
import com.n26.model.Transactions;
import com.n26.validation.factories.RequestValidatorFactory;

public class TransactionServiceTest extends BaseTestConfig {

	@Mock
	private RequestValidatorFactory RequestValidatorFactory;

	@InjectMocks
	private TransactionService transactionService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFinalStatisticsTest() {

		List<Transactions> statistincsTranactionsList = Arrays
				.asList(new Transactions().setAmount(BigDecimal.TEN).setTimestamp(Instant.now()));
		
		Statistics actualStats = new Statistics()
				.setAvg(new BigDecimal("10.00"))
				.setSum(new BigDecimal("10.00"))
				.setMax(new BigDecimal("10.00"))
				.setMin(new BigDecimal("10.00"))
				.setCount(1);

		Statistics expectedStats = transactionService.getFinalStatistics(statistincsTranactionsList);
		System.out.println("actualList "+actualStats);
		System.out.println("expectedList "+expectedStats);
		Assert.assertNotNull(expectedStats);
		Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedStats,actualStats));
	}

}
