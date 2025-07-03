package github.assessment.rewards.services;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.assessment.rewards.models.Transaction;

public class TransactionServiceJson implements TransactionService {

    private String dataFilename = "data.json";
	
	Map<String,List<Transaction>> userTransactions;
	
	@Override
	public void initService() {
		Transaction[] transactions = loadData(dataFilename);
		buildTransactionMap(transactions);
	}
	
	public void buildTransactionMap(Transaction[] transactions) {
		userTransactions = new HashMap<>();
		
    	for (Transaction transaction : transactions ) {
    		List<Transaction> currentTransactions = userTransactions.get(transaction.getUsername());
    		
    		if ( currentTransactions != null  ) {
    			currentTransactions.add(transaction);
    		}
    		else {
    			currentTransactions = new ArrayList<>();
    			currentTransactions.add(transaction);
    			userTransactions.put(transaction.getUsername(), currentTransactions);
    		}
    	}
	}
	
	public Transaction[] loadData(String filename) {
		
		Transaction[] data = null;
		
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        ClassPathResource resource = new ClassPathResource(filename);
	        InputStream inputStream = resource.getInputStream();

			data = objectMapper.readValue(inputStream, Transaction[].class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	@Override
	public List<Transaction> getTransactionsForUser(String userId) {
		return userTransactions.get(userId);
	}
	
	@Override
	public List<Transaction> getTransactionsForUserByMonth(String userId, int year, int month) {
		List<Transaction> transactions = getTransactionsForUser(userId);
		List<Transaction> transactionsForMonth = null;
		
		if ( transactions != null ) {
			transactionsForMonth = transactions
					.stream()
					.filter(transaction->checkTransactionMonth(transaction,year,month))
					.collect(Collectors.toList());
		}
		
		return transactionsForMonth;
	}
	
	private boolean checkTransactionMonth(Transaction  transaction, int year, int month) {
		LocalDate ld = LocalDate.parse( transaction.getDate() );
		return  ld.getYear() == year && ld.getMonthValue() == month;
	}

	public void setDataFilename(String dataFilename) {
		this.dataFilename = dataFilename;
	}
}
