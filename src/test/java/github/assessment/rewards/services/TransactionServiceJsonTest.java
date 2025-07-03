package github.assessment.rewards.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import github.assessment.rewards.models.Transaction;

class TransactionServiceJsonTest {

    @Test
    void testLoadData() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	Transaction[] transactions = service.loadData("testdata.json");
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.length, 3);
    }
    
    @Test
    void testGetTransactionsForUser1() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUser("gphillipp25");
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.size(), 1);
    }
    
    @Test
    void testGetTransactionsForUser2() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUser("mmiche1r");
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.size(), 2);
    }
    
    @Test
    void testGetTransactionsForUserDoesNotExist() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUser("abc");
    	
    	assertNull(transactions);
    }
    
    @Test
    void testGetTransactionsForUserByMonth1() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUserByMonth("gphillipp25",2025,04);
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.size(), 1);
    }
    
    @Test
    void testGetTransactionsForUserByMonthNoTransactions() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUserByMonth("gphillipp25",2025,05);
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.size(), 0);
    }
    
    @Test
    void testGetTransactionsForUserByMonth2() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUserByMonth("mmiche1r",2025,06);
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.size(), 2);
    }
    
    @Test
    void testGetTransactionsForUserByMonth2NoTransactions() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUserByMonth("mmiche1r",2025,04);
    	
    	assertNotNull(transactions);
    	assertEquals(transactions.size(), 0);
    }
    
    @Test
    void testGetTransactionsForUserByMonthUserDoesNotExist() {
    	TransactionServiceJson service = new TransactionServiceJson();
    	service.setDataFilename("testdata.json");
    	service.initService();
    	
    	List<Transaction> transactions =  service.getTransactionsForUserByMonth("abc",2025,04);
    	
    	assertNull(transactions);
    }

}
