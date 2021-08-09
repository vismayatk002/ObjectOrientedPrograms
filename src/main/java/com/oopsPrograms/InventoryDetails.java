package com.oopsPrograms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InventoryDetails {
    public static void main( String[] args )
    {
    	JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("Inventory.json"))
        {
            //Read JSON file
        	//converting string value in to json using parse
            JSONObject inventoryObject = (JSONObject) jsonParser.parse(reader);
            //iterate json object
            for (Object key : inventoryObject.keySet()) {
            	
            	//converting key into string and store into grocery variable
                String grocery = (String)key;
                //retrieve the value using the key 
                JSONObject propertyObject = (JSONObject) inventoryObject.get(grocery);
                //retrieve properties value in property object
                String name = (String) propertyObject.get("Name");
                long price = (long) propertyObject.get("Price");
                long weight = (long) propertyObject.get("Weight");
                float resultPrice = price * weight;
                //putting total value to property object
                propertyObject.put("total", resultPrice);
                System.out.println("Name : " + name);
                System.out.println("Price : " + price);
                System.out.println("weight : " + weight);
                inventoryObject.put(grocery, propertyObject);
                System.out.println("Price of " + weight + " kg " + name + " : " + resultPrice);
            }
            System.out.println("Updated JSON String :");
            System.out.println(inventoryObject.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
