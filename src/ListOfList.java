/*
 * Problem Statement:
 * 
 * Write a function in any language that takes as input a list 
 * of lists of integers. The function should return void, and 
 * print to standard output the number of times each integer 
 * appeared at each index in an inner list. So for instance:
 * countItem ([ [ 1,2,3], [1,3,4], [1,3] ])
 * 
 * Would output:
 * 1 @ 0 -> 3
 * 2 @ 1 -> 1
 * 3 @ 2 -> 1
 * 3 @ 1 -> 2
 * 4 @ 2 -> 1
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ListOfList {
	public static void countItem(List<List<Integer>> list) {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		for(List<Integer> innerList :list) {
			int index = 0;
			for(Integer i : innerList) {
			
				/*
				 * Create a key as <num>@<index> and value = count
				 */
				String key = i + " @ " + index;
				
				if(!output.containsKey(key)) {
					output.put(key, 1);
				} else {
					output.put(key, output.get(key)+1);
				}
				
				index++;
			}
		}
		
		for(String k : output.keySet()) {
			System.out.println(k + " -> " + output.get(k));			
		}
	}
	public static void main(String[] args) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list2.add(1);
		list2.add(3);
		list2.add(4);
		list3.add(1);
		list3.add(3);
		list.add(list1);
		list.add(list2);
		list.add(list3);

		countItem(list);
	}
}
