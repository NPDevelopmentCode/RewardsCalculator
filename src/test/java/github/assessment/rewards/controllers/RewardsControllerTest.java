package github.assessment.rewards.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import github.assessment.rewards.dto.RewardsReport;
import github.assessment.rewards.models.Transaction;
import github.assessment.rewards.services.TransactionService;
import github.assessment.rewards.services.TransactionServiceJson;

class RewardsControllerTest {
	
	private RewardsController controller;
	private TransactionService service;
	
	
	@BeforeEach
	void init() {
		controller = Mockito.spy(new RewardsController());
		service = Mockito.mock(TransactionServiceJson.class);
		Mockito.when(controller.createTransactionService(Mockito.anyString())).thenReturn(service);
		
		controller.setServiceClassName("serviceName");
		controller.create();
	}
	
	@Test
	void testGetRewardsForUser() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(57)));
		transactions.add(new Transaction(2,"abc","2025-06-27",BigDecimal.valueOf(68)));
		
		Mockito.when(service.getTransactionsForUser(Mockito.anyString())).thenReturn(transactions);
		ResponseEntity<RewardsReport> report = controller.getRewardsForUser("abc");
		
		assertNotNull(report);
		assertEquals(report.getStatusCode(), HttpStatus.OK);
		assertEquals(report.getBody().getRewardsPoints(), 25);
	}
	
	@Test
	void testGetRewardsForUserNotFound() {
		Mockito.when(service.getTransactionsForUser(Mockito.anyString())).thenReturn(null);
		ResponseEntity<RewardsReport> report = controller.getRewardsForUser("abc");
		
		assertNotNull(report);
		assertEquals(report.getStatusCode(), HttpStatus.NO_CONTENT);
		assertNull(report.getBody());
	}
	
	@Test
	void testGetRewardsForUserByMonth() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(57)));
		
		Mockito.when(service.getTransactionsForUserByMonth(Mockito.eq("abc"),Mockito.eq(2025), Mockito.eq(06))).thenReturn(transactions);
		ResponseEntity<RewardsReport> report = controller.getRewardsForUserByMonth("abc",2025,06);
		
		assertNotNull(report);
		assertEquals(report.getStatusCode(), HttpStatus.OK);
		assertEquals(report.getBody().getRewardsPoints(), 7);
	}
	
	@Test
	void testGetRewardsForUserByMonthNoTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		
		Mockito.when(service.getTransactionsForUserByMonth(Mockito.eq("abc"),Mockito.eq(2025), Mockito.eq(06))).thenReturn(transactions);
		ResponseEntity<RewardsReport> report = controller.getRewardsForUserByMonth("abc",2025,06);
		
		assertNotNull(report);
		assertEquals(report.getStatusCode(), HttpStatus.OK);
		assertEquals(report.getBody().getRewardsPoints(), 0);
	}
	
	@Test
	void testGetRewardsForUserByMonthUserNotFound() {
		Mockito.when(service.getTransactionsForUserByMonth(Mockito.eq("abc"),Mockito.eq(2025), Mockito.eq(06))).thenReturn(null);
		ResponseEntity<RewardsReport> report = controller.getRewardsForUserByMonth("abc",2025,06);
		
		assertNotNull(report);
		assertEquals(report.getStatusCode(), HttpStatus.NO_CONTENT);
		assertNull(report.getBody());
	}
}
