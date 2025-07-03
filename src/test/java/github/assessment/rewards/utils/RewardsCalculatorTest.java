package github.assessment.rewards.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import github.assessment.rewards.models.Transaction;

class RewardsCalculatorTest {

	@Test
	void testGetSingleRewardBasic() {
		Transaction transaction = new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(120));
		assertEquals(RewardsCalculator.getSingleReward(transaction), 90);		
	}
	
	@Test
	void testGetSingleRewardNoReward() {
		Transaction transaction = new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(5));
		assertEquals(RewardsCalculator.getSingleReward(transaction), 0);		
	}
	
	@Test
	void testGetSingleRewardNoRewardMax() {
		Transaction transaction = new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(5));
		assertEquals(RewardsCalculator.getSingleReward(transaction), 0);		
	}
	
	@Test
	void testGetSingleRewardNoRewardSinglePoint() {
		Transaction transaction = new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(57));
		assertEquals(RewardsCalculator.getSingleReward(transaction), 7);		
	}
	
	@Test
	void testGetSingleRewardNoRewardSinglePointMax() {
		Transaction transaction = new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(100));
		assertEquals(RewardsCalculator.getSingleReward(transaction), 50);		
	}
	
	@Test
	void testGetSingleRewardNoRewardDoublePoints() {
		Transaction transaction = new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(505));
		assertEquals(RewardsCalculator.getSingleReward(transaction), 860);		
	}
	
	@Test
	void testCalculateRewardsSingleRewards() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(57)));
		transactions.add(new Transaction(2,"abc","2025-06-25",BigDecimal.valueOf(68)));
		
		assertEquals(RewardsCalculator.calculateRewards(transactions), 25);		
	}
	
	@Test
	void testCalculateRewardsSingleAndDoublePoints() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(68)));
		transactions.add(new Transaction(2,"abc","2025-06-25",BigDecimal.valueOf(157)));
		
		assertEquals(RewardsCalculator.calculateRewards(transactions), 182);		
	}
	
	@Test
	void testCalculateRewardsDoublePoints() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(221)));
		transactions.add(new Transaction(2,"abc","2025-06-25",BigDecimal.valueOf(384)));
		
		assertEquals(RewardsCalculator.calculateRewards(transactions), 910);		
	}
	
	@Test
	void testCalculateRewardsNoPoints() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(21)));
		transactions.add(new Transaction(2,"abc","2025-06-25",BigDecimal.valueOf(43)));
		
		assertEquals(RewardsCalculator.calculateRewards(transactions), 0);		
	}
	
	@Test
	void testCalculateRewardsMix() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"abc","2025-06-25",BigDecimal.valueOf(86)));
		transactions.add(new Transaction(2,"abc","2025-06-25",BigDecimal.valueOf(489)));
		transactions.add(new Transaction(2,"abc","2025-06-25",BigDecimal.valueOf(17)));
		
		assertEquals(RewardsCalculator.calculateRewards(transactions), 864);		
	}
	
	@Test
	void testCalculateRewardsNoTransactions() {
		List<Transaction> transactions = new ArrayList<>();

		assertEquals(RewardsCalculator.calculateRewards(transactions), 0);		
	}

}
