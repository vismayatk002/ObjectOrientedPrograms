package com.oopsPrograms;

public class StockPortfolio {
		
	public static void main(String[] args) {
		
		StockAccount account = new StockAccount("Stock.json");
		account.printReport();
		System.out.println("\n------------------------------------");
		System.out.println("\nTotal Account value : " + account.valueOf());
		System.out.println("\n------------------------------------");
		account.printCompanyShares();
		System.out.println("\n------------------------------------");
		account.buy(1000, "TATATECH");
		account.printReport();
		System.out.println("\n------------------------------------");
		account.printCompanyShares();
		System.out.println("\n------------------------------------");
		account.sell(1, "AIRTEL");
		account.printReport();
		System.out.println("\n------------------------------------");
		account.printCompanyShares();
	}
	
}
