package com.mohan.json;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import com.mohan.model.Employee;

public class EmployeeJSONGenerator {
	
	public static void main(String[] args) throws IOException {
		OutputStream fos = new FileOutputStream("/Users/shankara/emp_stream.txt");
		/*
		try {
			fos = new FileOutputStream("/Users/shankara/emp_stream.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
		JsonGenerator jsonGenerator = Json.createGenerator(fos);
		/**
         * We can get JsonGenerator from Factory class also
         * JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
         * jsonGenerator = factory.createGenerator(fos);
         */
		
		Employee emp = EmployeeJSONWriter.createNewEmployee();
		jsonGenerator.writeStartObject();
		jsonGenerator.write("id", emp.getId());
		jsonGenerator.write("name", emp.getName()).write("role", emp.getRole());
		
		jsonGenerator.writeStartObject("address")
				.write("street",emp.getAddress().getStreet())
				.write("city",emp.getAddress().getCity())
				.write("zipcode",emp.getAddress().getZipcode())
				.writeEnd();
		
		jsonGenerator.writeStartArray("phoneNumbers");
		for ( Long phoneNum : emp.getPhoneNumbers())
			jsonGenerator.write(phoneNum);
		
		jsonGenerator.writeEnd();
		jsonGenerator.writeEnd();
		
		//System.out.println("Emp object: \n" +jsonGenerator.toString());
		jsonGenerator.close();
		
	}
}
