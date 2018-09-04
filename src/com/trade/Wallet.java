package com.trade;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Wallet {
	long transanctionId;
	public long getTransanctionId() {
		return transanctionId;
	}
	public void setTransanctionId(long transcationId) {
		this.transanctionId = transcationId;
	}
	long accountNumber;
	String userId;
	String fullName;
	String direction;
	Timestamp timeStamp;
	BigDecimal amount;
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUserId() {
		return userId;
	}
	
	public Wallet(){
		
	}
	
	public Wallet(long accountNumber, String userId, String fullName, String direction, Timestamp timeStamp,
			BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.userId = userId;
		this.fullName = fullName;
		this.direction = direction;
		this.timeStamp = timeStamp;
		this.amount = amount;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
