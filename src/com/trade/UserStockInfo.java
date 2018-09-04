package com.trade;

import java.math.BigDecimal;

public class UserStockInfo {

	
	private long id;
	String userId;
	String securityName;
	String securityCode;
	String securityType;
	long totalQuantity;
	BigDecimal totalPrice;
	
	public UserStockInfo(String userId, String securityName, String securityCode, String securityType,
			long totalQuantity, BigDecimal totalPrice) {
		super();
		this.userId = userId;
		this.securityName = securityName;
		this.securityCode = securityCode;
		this.securityType = securityType;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
	}
	
	public UserStockInfo() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void updateStockInfoTable(String userId2, String securityName2, String securityCode2, String securityType2,
			long quantity, BigDecimal priceOfSecurity) {
		// TODO Auto-generated method stub
		
	}
}
