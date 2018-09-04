package com.trade;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class InProgress {
	long orderId;
	String userId;
	String userName;
	String securityName;
	String securityCode;
	String securityType;
	long quantity;
	BigDecimal priceOfSecurity;
	Timestamp timeStamp;
	String currency;
	String direction;
	String status;
	String tradeType;
	BigDecimal totalPrice;
	long remainingQuantity;
	public long getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(long remainingquantity) {
		this.remainingQuantity = remainingquantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = (totalPrice);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getDirection() {
		if(direction.equals("B")) {
			return "Buy";
		}else if(direction.equals("S")){
			return "Sell";
		}
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void add(BigDecimal totalPrice){
		this.totalPrice= this.totalPrice.add(totalPrice);
	}
	
	

}
