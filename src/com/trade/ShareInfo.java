package com.trade;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ShareInfo {

	String securityCode;
	String securityName;
	String securityType;
	long quantityAvailable;
	BigDecimal priceOfSecurity;
	String symbol;
	
	BigDecimal changedPrice;
	public BigDecimal getChangedPrice() {
		return changedPrice;
	}
	public void setChangedPrice(BigDecimal changedPrice) {
		this.changedPrice = changedPrice;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	public long getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public BigDecimal getPriceOfSecurity() {
		return priceOfSecurity;
	}
	public void setPriceOfSecurity(BigDecimal priceOfSecurity) {
		this.priceOfSecurity = priceOfSecurity;
	}
	
}
