package com.oopsPrograms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockPortfolio {
	
	static FileReader reader;
	static JSONObject stockObject;
	static HashMap<String, StockAccount> stockMap = new HashMap<>();
	
	public static void readFile() {
		
		try {
			reader = new FileReader("stock.json");
			JSONParser jsonParser = new JSONParser();
			//converting string value in to json using parse
            JSONObject stockObject = (JSONObject) jsonParser.parse(reader);
            getDetails(stockObject);
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	public static void getDetails(JSONObject stockObject) {
		
		for (Object key : stockObject.keySet()) {
			
        	//converting key into string and store into grocery variable
            String name = (String)key;
            //retrieve the value using the key 
            JSONObject shareObject = (JSONObject) stockObject.get(name);
            //retrieve properties value in property object
            long price = (long) shareObject.get("price");
            long no_of_shares = (long) shareObject.get("no_of_shares");
            StockAccount stock = new StockAccount(name, price, no_of_shares);
            stock.setTotalStockValue();
            stockMap.put(name, stock);
        }
	}
	public static void showPortfolio() {
		
		for (String companyKey : stockMap.keySet()) {
			StockAccount stock = stockMap.get(companyKey);
			System.out.println("\nCompany Name : " + stock.getName());
			System.out.println("\nPrice : " + stock.getPrice());
			System.out.println("\nNo of shares : " + stock.getNo_of_shares());
			System.out.println("\nTotal Stock value : " + stock.getTotalStockValue());
		}
	}
	public static void main(String[] args) {
		
		readFile();
		showPortfolio();
	}
	
}
