package com.zzrenfeng.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMap {

	public static void main(String[] args) {
		List rstList = new ArrayList<>();
		
		Map testMap = new HashMap<>();
		
		testMap.put("name", "zhangsan");
		testMap.put("sex", "male");
		testMap.put("age", 24);
		testMap.put("key", "201500001");
		testMap.put("value", "41082319XXX");
		//testMap.clear();
		testMap.remove("key");
		testMap.put("key", "change");
		testMap.remove("value", "41082319XXX");	
		testMap.put("key", "change-change");
		
		rstList.add(testMap);
		
		testMap = new HashMap<>();
		testMap.put("name", "lisi");
		testMap.put("sex", "female");
		testMap.put("age", 23);
		testMap.put("key", "201500002");
		testMap.put("value", "41082320XXX");
		
		rstList.add(testMap);
		
		testMap = new HashMap<>();
		testMap.put("name", "wangwu");
		testMap.put("sex", "male");
		testMap.put("age", 25);
		testMap.put("key", "201500003");
		testMap.put("value", "41082320XXX");
		
		rstList.add(testMap);
		
//		System.out.println(testMap.toString());
//		System.out.println(testMap.containsKey("key"));
		
		System.out.println(rstList.get(0).toString());
		
	}

}
