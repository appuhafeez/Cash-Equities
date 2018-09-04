package com.trade;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserHistory {

	long transactionId;
	public UserHistory() {
		super();
	}
	String userIdB;
	long orderIdB;
	String userIdS;
	long orderIdS;
	String securityName;
	String securityCode;
	String securityType;
	long quantity;
	BigDecimal priceOfSecurity;
	Timestamp timeStamp;
	String currency;
	public UserHistory(String userIdB, long orderIdB, String userIdS, long orderIdS, String securityName,
			String securityCode, String securityType, long quantity, BigDecimal priceOfSecurity, Timestamp timeStamp,
			String currency) {
		super();
		this.userIdB = userIdB;
		this.orderIdB = orderIdB;
		this.userIdS = userIdS;
		this.orderIdS = orderIdS;
		this.securityName = securityName;
		this.securityCode = securityCode;
		this.securityType = securityType;
		this.quantity = quantity;
		this.priceOfSecurity = priceOfSecurity;
		this.timeStamp = timeStamp;
		this.currency = currency;
	}
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(String userIdB) {
		this.userIdB = userIdB;
	}
	public long getOrderIdB() {
		return orderIdB;
	}
	public void setOrderIdB(long orderIdB) {
		this.orderIdB = orderIdB;
	}
	public String getUserIdS() {
		return userIdS;
	}
	public void setUserIdS(String userIdS) {
		this.userIdS = userIdS;
	}
	public long getOrderIdS() {
		return orderIdS;
	}
	public void setOrderIdS(long orderIdS) {
		this.orderIdS = orderIdS;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPriceOfSecurity() {
		return priceOfSecurity;
	}
	public void setPriceOfSecurity(BigDecimal priceOfSecurity) {
		this.priceOfSecurity = priceOfSecurity;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
