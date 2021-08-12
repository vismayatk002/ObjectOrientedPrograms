package com.oopsPrograms;

public class CompanyShares {
	
	String symbol,transaction_time; 
	long no_of_shares;
	
	public CompanyShares( String symbol, long no_of_shares) {
		
		this.symbol = symbol;
		this.no_of_shares = no_of_shares;
	}
	public String getTransactionTime(){
        return transaction_time;
	}
	public void setTransactionTime(String transaction_time){
        this.transaction_time = transaction_time;
	}
	public String getSymbol(){
        return symbol;
	}
	public long getNumberOfShares(){
        return no_of_shares;
	}
	public void setNumberOfShares(long newShare){
        this.no_of_shares += newShare;
	}
	
}
