/*
 * Problem Statement:
 * 
 * Given a set of items, each with a weight and a value, determine the number of 
 * each item to include in a collection so that the total weight is less than or 
 * equal to a given limit and the total value is as large as possible.
 */

import java.util.ArrayList;


public class Knapsack {
	
	public Knapsack() {
		
		/*
		 * Initialize the list of available items with their weights and values.
		 * The weights and values should be positive. 
		 */
		ArrayList<Item> items = new ArrayList<Item>();		
		items.add(new Item(2, 3));
		items.add(new Item(3, 5));
		items.add(new Item(3, 7));
		items.add(new Item(4, 4));
		items.add(new Item(4, 3));
		items.add(new Item(5, 9));
		items.add(new Item(7, 2));
		items.add(new Item(8, 11));
		items.add(new Item(8, 5));
		
		/*
		 * Maximum weight that a knapsack can handle
		 */
		float W = 15;
		
		float maxValue = getMaxValue_01knapsack_recursive(items, items.size(), 0, 0, W);
		
		System.out.println("Maximum value = " + maxValue);
	}
	

	/**
	 * Computes the maximum value possible from input items. Solved for 0/1 knapsack 
	 * problem, where the number of copies of each kind of item is either 0 or 1.
	 * 
	 * The maximum value is computed by considering all possible combination of items 
	 * from the input list using recursion.
	 * 
	 * @param items List of input items with their weights and values
	 * @param num Number of items to be considered from the input list
	 * @param val Current total value of items in the knapsack
	 * @param currentWeight Current total weight of items in the knapsack
	 * @param W Maximum weight that a knapsack can handle
	 * @return Maximum value possible by putting appropriate items in the knapsack
	 */
	private float getMaxValue_01knapsack_recursive(ArrayList<Item> items, int num, float val, float currentWeight, float W) {
		/*
		 * If there are no items in the list, return whatever value we have till now
		 */
		if(num <= 0) {
			return val;
		}
		
		/*
		 * Current item considered is the last item in the list. The last item is indexed at 'num'
		 */
		Item currentItem = items.get(num-1);
		float valWithCurrentItem = 0, valWithoutCurItem = 0;
		
		/*
		 * Compute total value by including the current item, if its weight can be handled 
		 * by the knapsack		
		 */
		if((currentWeight + currentItem.getWeight()) <= W) {
			valWithCurrentItem = getMaxValue_01knapsack_recursive(items, num-1, val + currentItem.getValue(), currentWeight + currentItem.getWeight(), W);
		}
		
		/*
		 * Compute total value by excluding the current item.
		 */
		valWithoutCurItem = getMaxValue_01knapsack_recursive(items, num-1, val, currentWeight, W);
		
		/*
		 * Return the maximum of values obtained from 2 options considered above, i.e. including 
		 * the current item and excluding the current item.
		 */
		return Math.max(valWithCurrentItem, valWithoutCurItem);
	}

	public static void main(String[] args) {
		new Knapsack();
	}
}

/**
 * This class represents an Item that will be used in the knapsack problem. 
 * Each item has its own weight and value.
 * @author sujal
 *
 */
class Item {
	private float value;
	private float weight;
	
	public Item(float val, float wt) {
		this.value = val;
		this.weight = wt;
	}

	public float getValue() {
		return value;
	}

	public float getWeight() {
		return weight;
	}
}