package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.model.Statistics;
import com.n26.service.TransactionService;

/**
 * Rest controller to handle all end point related to staticstics resource.
 * @author Pratik
 *
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	TransactionService transactionService;

	@GetMapping
	public ResponseEntity<Statistics> getStatistics() {
		return transactionService.calculateStatistics();
	}
}
