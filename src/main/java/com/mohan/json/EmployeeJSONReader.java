package com.mohan.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.mohan.model.Address;
import com.mohan.model.Employee;

public class EmployeeJSONReader {
	
	public static final String JSON_FILE = "/Users/shankara/m1.json";
	
	public static void main(String[] args) throws IOException {
		InputStream fis = new FileInputStream(JSON_FILE);
		
		JsonReader jsonReader = Json.createReader(fis);
		JsonObject jsonObject = jsonReader.readObject();
		/**
         * We can create JsonReader from Factory also
        JsonReaderFactory factory = Json.createReaderFactory(null);
        jsonReader = factory.createReader(fis);
        */
		
		jsonReader.close();
		fis.close();
		
		Employee emp = new Employee();
		
		emp.setId(jsonObject.getInt("id"));
		emp.setName(jsonObject.getString("name"));
		emp.setPermanent(jsonObject.getBoolean("permanent"));
		emp.setRole(jsonObject.getString("role"));
		
		JsonObject addressJsonObject = jsonObject.getJsonObject("address");
		Address address = new Address();
		address.setCity(addressJsonObject.getString("city"));
		address.setStreet(addressJsonObject.getString("street"));
		address.setZipcode(addressJsonObject.getInt("zipcode"));
		emp.setAddress(address);
		
		JsonArray jsonArray = jsonObject.getJsonArray("phoneNumbers");
		List<Long> list = new ArrayList<Long>();
		for ( JsonValue value : jsonArray )
			list.add(Long.parseLong(value.toString()));
		emp.setPhoneNumbers(list);
		
		// Print employee bean:
		System.out.println("Employee:\n" + emp);	
	}

}
