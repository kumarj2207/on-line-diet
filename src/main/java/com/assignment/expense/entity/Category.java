package com.assignment.expense.entity;

import java.util.HashMap;
import java.util.Map;

public class Category {
	
	private static final Map<Integer, String> categories = new HashMap<>();
	
	static {
			categories.put(1,"fuel".toUpperCase() );
			categories.put(2,"entertainment".toUpperCase() );
			categories.put(3,"medical".toUpperCase() );
			categories.put(4,"food".toUpperCase() );
			categories.put(5,"grossery".toUpperCase() );
			categories.put(6,"vivaan".toUpperCase() );
			categories.put(7,"shoping".toUpperCase() );
			categories.put(8,"mobileandinternet".toUpperCase() );
			categories.put(9,"other".toUpperCase() );
	}
	
	public static String getCategory(Integer key) {
		return categories.get(key);
	}

}
