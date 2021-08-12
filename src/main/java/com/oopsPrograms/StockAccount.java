package com.oopsPrograms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount {
	
	static HashMap<String, Stock> stockMap = new HashMap<>();
	static ArrayList<CompanyShares> shareList = new ArrayList<CompanyShares>();
	
	public StockAccount(String filename) {
		
		try {
			FileReader reader = new FileReader(filename);
			JSONParser jsonParser = new JSONParser();
			//converting string value in to json using parse
            JSONObject stockObject = (JSONObject) jsonParser.parse(reader);
            setInitialStockDetails(stockObject);
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	public static void setInitialStockDetails(JSONObject stockObject) {
		
		for (Object key : stockObject.keySet()) {
			
        	//converting key into string and store into grocery variable
            String name = (String)key;
            //retrieve the value using the key 
            JSONObject shareObject = (JSONObject) stockObject.get(name);
            //retrieve properties value in property object
            String symbol = (String) shareObject.get("symbol");
            long price = (long) shareObject.get("price");
            long no_of_shares = (long) shareObject.get("no_of_shares");
            
            Stock stock = new Stock(name, symbol, price, no_of_shares);
            stockMap.put(name, stock);
            
            // Creating and adding CompanyShares to list
            CompanyShares share = new CompanyShares(symbol,no_of_shares);
            shareList.add(share);
        }
	}
	public void printReport() {
		
		for (String companyKey : stockMap.keySet()) {
			Stock stock = stockMap.get(companyKey);
			System.out.println("\nCompany Name : " + stock.getName());
			System.out.println("\nSymbol : " + stock.getSymbol());
			System.out.println("\nPrice : " + stock.getPrice());
			System.out.println("\nNo of shares : " + stock.getNumberOfShares());
			System.out.println("\nTotal Stock value : " + stock.getTotalStockValue());
		}
	}
	public double valueOf() {
		
		double stockValue = 0;
		
		for (String companyKey : stockMap.keySet()) {
			Stock stock = stockMap.get(companyKey);
			// Calculating total share value
			stockValue += (double) stock.getTotalStockValue();
		}
		return stockValue;
	}
	public void buy(int amount,String symbol) {
		for (String companyKey : stockMap.keySet()) {
			Stock stock = stockMap.get(companyKey);
			
			if(stock.getSymbol().equals(symbol)) {
				// get number of shared based on amount and given price
				long newShare = (long)(amount/stock.getPrice());
				stock.setNumberOfShares(newShare);
				// Update the HashMap value with the updated stock object
				stockMap.put(companyKey, stock);
				
				// set value to list
				for (int i = 0; i < shareList.size(); i++) {
					// Get the symbol from array list object
					String tempSymbol = shareList.get(i).getSymbol();				
					if(tempSymbol.equals(symbol)) {
						//Temporarily store the CompanyShare object for updation
						CompanyShares tempCompanyShare = shareList.get(i); 
						tempCompanyShare.setNumberOfShares(newShare);
						
						// Set transaction time						
						tempCompanyShare.setTransactionTime(getTime());
						shareList.set(i, tempCompanyShare);
					}
				}  
			}
		}
		
	}
	public void sell(long shares,String symbol) {
		
		for (String companyKey : stockMap.keySet()) {
			Stock stock = stockMap.get(companyKey);
			if(stock.getSymbol().equals(symbol)) {
				stock.setNumberOfShares(-shares);
				stockMap.put(companyKey, stock);
				
				// set value to list
				for (int i = 0; i < shareList.size(); i++) {
					String tempSymbol = shareList.get(i).getSymbol();				
					if(tempSymbol.equals(symbol)) {
						CompanyShares tempCompanyShare = shareList.get(i); 
						tempCompanyShare.setNumberOfShares(-shares);
						
						// Set transaction time						
						tempCompanyShare.setTransactionTime(getTime());
						shareList.set(i, tempCompanyShare);
					}
				}
			}
		}
	}
	public String getTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String transactionTime = myDateObj.format(myFormatObj);
		return transactionTime;
	}
	
	public void printCompanyShares() {
		for (CompanyShares share : shareList){
			System.out.println("\nSymbol : " + share.getSymbol());
			System.out.println("\nNo of shares : " + share.getNumberOfShares());
			System.out.println("\nlast Transaction : " + share.getTransactionTime());
		}
	}
}
