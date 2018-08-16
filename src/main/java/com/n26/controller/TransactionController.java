package com.n26.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.model.Transactions;
import com.n26.service.TransactionService;

/**
 * Rest controller to handle all end point related to transactions resource.
 * @author Pratik
 *
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping
	public ResponseEntity createTransaction(@Valid @RequestBody Transactions transactions) {
		return transactionService.createTransaction(transactions);
	}
	
	@DeleteMapping
	public ResponseEntity deleteTransactions() {
		return transactionService.deleteTransactions();
	}
}
