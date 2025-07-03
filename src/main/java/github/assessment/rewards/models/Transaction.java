package github.assessment.rewards.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
	private int id;
	private String username;
	private String date;
	private BigDecimal totalPurchase;
	
	public Transaction() {
		super();
	}
	
	public Transaction(int id, String username, String date, BigDecimal totalPurchase) {
		super();
		this.id = id;
		this.username = username;
		this.date = date;
		this.totalPurchase = totalPurchase;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String data) {
		this.date = data;
	}
	
	@JsonProperty("total_purchase")
	public BigDecimal getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(BigDecimal totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
}
