package com.n26.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.BaseTestConfig;
import com.n26.model.Transactions;
import com.n26.service.TransactionService;

public class TransactionControllerTest extends BaseTestConfig {

	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Mock
	private TransactionService transactionService;
	
	@InjectMocks
	private TransactionController transactionController;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(transactionController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createTransactionTest() throws JsonProcessingException, Exception {

		Transactions transactions = new Transactions();
		transactions.setAmount(BigDecimal.ONE).setTimestamp(Instant.now());

		when(transactionService.createTransaction(transactions))
				.thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
		
		mvc.perform(post("/transactions").content(objectMapper.writeValueAsString(transactions))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	public void createTransaction_InvalidParameter_400Error() throws JsonProcessingException, Exception {

		Transactions transactions = new Transactions();
		transactions.setAmount(BigDecimal.ONE);

		when(transactionService.createTransaction(transactions))
				.thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
		
		mvc.perform(post("/transactions").content(objectMapper.writeValueAsString(transactions))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}
}
