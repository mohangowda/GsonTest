package com.mohan.json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import com.mohan.model.Address;
import com.mohan.model.Employee;

public class EmployeeJSONWriter {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Employee emp = createNewEmployee();
		
		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addBuilder = Json.createObjectBuilder();
		JsonArrayBuilder phoneBuilder = Json.createArrayBuilder();
		
		for (Long phoneNumber : emp.getPhoneNumbers())
			phoneBuilder.add(phoneNumber);
		
		addBuilder.add("city", emp.getAddress().getCity())
					.add("street", emp.getAddress().getStreet())
					.add("zipcode", emp.getAddress().getZipcode());
		
		empBuilder.add("id", emp.getId()).add("name", emp.getName()).add("role", emp.getRole());
		empBuilder.add("phoneNumbers", phoneBuilder);
		empBuilder.add("address", addBuilder);
		
		JsonObject jsonObject = empBuilder.build();
		System.out.println("Employee Object: \n" + jsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("/Users/shankara/emp.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		/**
         * We can get JsonWriter from JsonWriterFactory also
        JsonWriterFactory factory = Json.createWriterFactory(null);
        jsonWriter = factory.createWriter(os);
        */
		jsonWriter.writeObject(jsonObject);
		jsonWriter.close();
			
	}
	
	public static Employee createNewEmployee() {
		Employee emp = new Employee();
		emp.setId(1000);
		emp.setName("Mohan Gowda");
		emp.setPermanent(true);
		emp.setRole("Manager");
		
		List<Long> phoneList = new ArrayList<Long>();
		phoneList.add(123456789L);
		phoneList.add(34567890245L);
		emp.setPhoneNumbers(phoneList);
		
		Address address = new Address();
		address.setCity("San Jose");
		address.setStreet("209765 befglk");
		address.setZipcode(95014);
		emp.setAddress(address);
		
		return emp;
	}

}
