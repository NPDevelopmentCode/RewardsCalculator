package github.assessment.rewards.services;

import java.util.List;

import github.assessment.rewards.models.Transaction;

public interface TransactionService {
	
	public void initService();
	
	public List<Transaction> getTransactionsForUser(String userId);
	
	public List<Transaction> getTransactionsForUserByMonth(String userId, int year, int month);
}
