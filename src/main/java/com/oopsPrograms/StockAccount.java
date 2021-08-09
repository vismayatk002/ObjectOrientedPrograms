package com.oopsPrograms;

public class StockAccount {
	
	String name; 
	long price;
	long no_of_shares;
	float totalStockValue;
	public StockAccount(String name, long price, long no_of_shares) {
		
		this.name = name;
		this.price = price;
		this.no_of_shares = no_of_shares;
//		this.totalHoldings =price * no_of_shares;
	}
	public String getName(){
        return name;
	}
	public long getPrice(){
        return price;
	}
	public long getNo_of_shares(){
        return no_of_shares;
	}
	public void setTotalStockValue() {
		this.totalStockValue = this.price * this.no_of_shares;
	}
	public float getTotalStockValue(){
        return totalStockValue;
	}
	
}
