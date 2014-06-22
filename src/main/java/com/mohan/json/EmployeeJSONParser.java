package com.mohan.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import com.mohan.model.Address;
import com.mohan.model.Employee;

public class EmployeeJSONParser {

	public static final String FILE_NAME = "/Users/shankara/emp.txt";
	
	public static void main(String[] args) throws IOException {
		InputStream fis = new FileInputStream(FILE_NAME);
		
		JsonParser jsonParser = Json.createParser(fis);
		
		/**
         * We can create JsonParser from JsonParserFactory also with below code
         * JsonParserFactory factory = Json.createParserFactory(null);
         * jsonParser = factory.createParser(fis);
         */
		
		Employee emp = new Employee();
		Address address = new Address();
		String keyName = null;
		List<Long> phoneNums = new ArrayList<Long>();
		
		while(jsonParser.hasNext()) {
			Event event = jsonParser.next();
			
			switch(event) {
			case KEY_NAME:
				keyName = jsonParser.getString();
				break;
			case VALUE_STRING:
				setStringValues(emp, address, keyName, jsonParser.getString());
				break;
			case VALUE_NUMBER:
				setNumberValues(emp, address, phoneNums, keyName, jsonParser.getLong());
				break;
			case VALUE_TRUE:
				setBooleanValues(emp, keyName, true);
				break;
			case VALUE_FALSE:
				setBooleanValues(emp, keyName, false);
				break;
			default:
					//no other types..
			}
		}
		emp.setAddress(address);
		emp.setPhoneNumbers(phoneNums);

		System.out.println("Empoyee: \n" + emp);
		
		//close resource
		fis.close();
		jsonParser.close();
	}
	
	public static void setNumberValues(Employee emp, Address address, List<Long> phoneNums, String key, long value) {
		switch(key){
		case "zipcode" :
			address.setZipcode((int)value);
			break;
		case "id":
			emp.setId((int)value);
			break;
		case "phoneNumbers":
			phoneNums.add(value);
			break;
		default:
			System.out.println("Unknown element with key: " + key);
		}
	}
	
	public static void setStringValues(Employee emp, Address address, String key, String value) {
		switch(key){
        case "name":
            emp.setName(value);
            break;
        case "role":
            emp.setRole(value);
            break;
        case "city":
            address.setCity(value);
            break;
        case "street":
            address.setStreet(value);
            break;
        default:
            System.out.println("Unknown Key="+key);
		}
	}

	public static void setBooleanValues(Employee emp, String key, boolean value) {
		if("permanent".equals(key)){
            emp.setPermanent(value);
        }else{
            System.out.println("Unknown element with key="+key);
        }
	}
}
