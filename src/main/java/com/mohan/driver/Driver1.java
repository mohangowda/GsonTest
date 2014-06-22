package com.mohan.driver;

import java.util.ArrayList;
import java.util.List;

public class Driver1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Long> list = new ArrayList<Long>();
		list.add(123456789L);
		list.add(234567890L);
		
		System.out.println("List: " + list.toString());
		
		Long lobj = 12345678l;
		long l = lobj.longValue();
		long l1 = lobj;
		System.out.println("mylong: " + lobj + "\n l: " + l + "\n l1: " + l1);
		
		
		String str = null;
		System.out.println("Mys string: "+ str.substring(1));

	}

}
