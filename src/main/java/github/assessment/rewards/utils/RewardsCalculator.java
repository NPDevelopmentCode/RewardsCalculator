package github.assessment.rewards.utils;

import java.math.RoundingMode;
import java.util.List;

import github.assessment.rewards.models.Transaction;

public class RewardsCalculator {
	
	static public int calculateRewards(List<Transaction> transactions) {	
		return transactions
			.stream()
			.map(transaction->getSingleReward(transaction))
			.reduce(0, (sum, singleReward)->sum+singleReward);
	}
	
	static public int getSingleReward(Transaction transaction) {
		int roundedDown = transaction.getTotalPurchase()
				                     .setScale(0, RoundingMode.FLOOR)
				                     .intValueExact();
		
		int reward = 0;
		
		if ( roundedDown <= 50 ) {
			reward = 0;
		}
		else if ( 50 < roundedDown &&  roundedDown <= 100 ) {
			reward = roundedDown - 50;
		}
		else {
			reward = 50 + 2 * (roundedDown - 100);
		}
		
		return reward;
	}
}
