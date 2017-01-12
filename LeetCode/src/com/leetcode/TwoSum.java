package com.leetcode;

import java.util.HashMap;
import java.util.Map;


/************************************************************************************
 * 题目大意：给出n个数，在其中找出和为一个特定数的两个数,输出其位置（位置从左到右是从1开始）。要求输出的第一个数字比第二个小，假设这样的数字组合只有一组。
 * 大致解法：Hash、双指针。

题目描述：
Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target,
 where index1 must be less than index2. 
 Please note that your returned answers (both index1 and index2) are not zero-based（从零开始）.
You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 * 
 * *****************************************************************************************************************/
public class TwoSum {

	
	/**************************************************************************************************
	 * Approach 1
	 * Time complexity : O(n^2) .
	 * For each element, we try to find its complement by looping through the rest of array which takes O(n)O(n) time. 
	 * 
	 * Space complexity : O(1)
	 * **********************************************************************************************************/
	public static int[] twoSum1(int[] numbers, int target) {
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i+1; j < numbers.length; j++) {
				if (target - numbers[i] ==   numbers[j]) {
					return new int[]{ i+1,j+1};
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
		
	}
	
	
	
	/**************************************************************************************************
	 * Approach 2
	 * A hash table supports fast look up in near constant time
	 * 
	 * Time complexity : O(n) .
	 * We traverse the list containing nn elements exactly twice. Since the hash table reduces the look up time to O(1)
	 * 
	 * Space complexity : O(n)
	 * The extra space required depends on the number of items stored in the hash table, which stores exactly n elements.
	 * **********************************************************************************************************/
	public static int[] twoSum2(int[] numbers, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			map.put(numbers[i], i);
		}
		
		for (int i = 0; i < numbers.length; i++) {
			int completment = target - numbers[i];
			if (map.containsKey(completment) && completment != numbers[i]) {
				return new int[]{ i,map.get(completment)};
			}
		}
		throw new IllegalArgumentException("No two sum solution");
		
	}
	
	
	
	/**************************************************************************************************
	 * Approach 3
	 * A hash table supports fast look up in near constant time
	 * 
	 * Time complexity : O(n) .
	 * We traverse the list containing n elements only once. Each look up in the table costs only O(1) time
	 * 
	 * Space complexity : O(n)
	 * The extra space required depends on the number of items stored in the hash table, which stores at most n elements
	 * **********************************************************************************************************/
	public static int[] twoSum3(int[] numbers, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			int completment = target - numbers[i];
			if (map.containsKey(completment)) {
				return new int[]{map.get(completment),i};
			}
			map.put(numbers[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
	

	

	/**********************************************
	 * @param args
	 *********************************************/
	public static void main(String[] args) {
		
		 int[] numbers = {2, 7, 11, 15,18,13,9,3,16,5};
		 int target = 33;//保证只有一组解
		 
		 int[] result = twoSum1(numbers,target);
		 
		 System.out.println("index1="+result[0]+",index2="+result[1]);
		 
	}

}
