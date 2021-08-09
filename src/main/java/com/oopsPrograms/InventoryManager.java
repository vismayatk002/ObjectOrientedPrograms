package com.oopsPrograms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InventoryManager {
	
	static FileReader reader;
	static JSONObject inventoryObject;
	public void readFile() {
		try {
			//Read JSON file
			reader = new FileReader("Inventory.json");
			JSONParser jsonParser = new JSONParser();
        	//converting string value in to json using parse
            inventoryObject = (JSONObject) jsonParser.parse(reader);
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	public static void inventoryFactory() {
		
		//iterate json object
        for (Object key : inventoryObject.keySet()) {
        	
        	//converting key into string and store into grocery variable
            String grocery = (String)key;
            //retrieve the value using the key 
            JSONObject propertyObject = (JSONObject) inventoryObject.get(grocery);
            //retrieve properties value in property object
            long price = (long) propertyObject.get("price");
            long weight = (long) propertyObject.get("weight");
            float resultPrice = calcPrice(price, weight);
            propertyObject.put("total", resultPrice);
            inventoryObject.put(grocery, propertyObject);
        }
	}
	public static float calcPrice(long price, long weight ) {
		
		
		float resultPrice = price * weight;
		//putting total value to property object
        return resultPrice;
	}
	public static void main( String[] args ) {
  
		InventoryManager inventory = new InventoryManager();
		inventory.readFile();
		inventory.inventoryFactory();
        System.out.println("Updated JSON String :");
        System.out.println(inventoryObject.toString()); 
    }
}
