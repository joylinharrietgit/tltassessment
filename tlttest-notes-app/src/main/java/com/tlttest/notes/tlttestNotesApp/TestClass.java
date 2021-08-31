package com.tlttest.notes.tlttestNotesApp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestClass {
	private static String test = "I want to go to the want park";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] arr = test.split(" ");
		Map<String, Integer> map = new HashMap<>();

		for (int i=0 ; i < arr.length ; i++){
			if (!map.containsKey(arr[i])){
				map.put(arr[i],1);
			} else{
				map.put(arr[i],map.get(arr[i])+1);
			}
		}
		System.out.println("Map with word frequency is "+ map);
		printSortedMap(map);
	}
	private static void printSortedMap(Map<String, Integer> unSortedMap) {
        
        System.out.println("Unsorted Map : " + unSortedMap);
 
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
         
        System.out.println("Sorted Map   : " + sortedMap);
         
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
         
        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
		
	}

}
