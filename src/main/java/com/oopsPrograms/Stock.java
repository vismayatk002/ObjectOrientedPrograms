package com.oopsPrograms;

public class Stock {
	
	String name,symbol; 
	long price;
	long no_of_shares;
	
	public Stock(String name, String symbol, long price, long no_of_shares) {
		
		this.name = name;
		this.symbol = symbol;
		this.price = price;
		this.no_of_shares = no_of_shares;
	}
	public String getName(){
        return name;
	}
	public long getPrice(){
        return price;
	}
	public String getSymbol(){
        return symbol;
	}
	public void setNumberOfShares(long newShare){
        this.no_of_shares += newShare;
	}
	public long getNumberOfShares(){
        return no_of_shares;
	}
	public float getTotalStockValue(){
        return this.price * this.no_of_shares;
	}
	
}
