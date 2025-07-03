package github.assessment.rewards.dto;

public class RewardsReport {
	String userId;
	int rewardsPoints;

	public RewardsReport(String userId, int rewardsPoints) {
		super();
		this.userId = userId;
		this.rewardsPoints = rewardsPoints;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRewardsPoints() {
		return rewardsPoints;
	}
	public void setRewardsPoints(int rewardsPoints) {
		this.rewardsPoints = rewardsPoints;
	}
}
