package com.hk.algo;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a binary string, count number of substrings that start and end with 1. 
 * For example, if the input string is ¡°00100101¡±, then there are 
 * three substrings ¡°1001¡±, ¡°100101¡± and ¡°101¡±.
 */
public class FindOneXOne {

	private String input = null;
	private char[] inputArray = null;
	private List<String> subStrings = new ArrayList<String>();
	private int count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindOneXOne find = new FindOneXOne();
		find.cal();
	}
	
	public void init()
	{
		input = "00100101";
		inputArray = input.toCharArray();
	}
	
	
	public void cal()
	{
		init();
		for(int i =0;i<inputArray.length;i++)
		{
			if(inputArray[i]=='0')
			{
				continue;
			}
			else
			{
				for(int j =i+1;j<inputArray.length;j++)
				{
					if(inputArray[j]=='1')
					{
						String sub = new String(inputArray,i,j-i+1);
						subStrings.add(sub);
					}
					count++;
				}
			}
				
		}
		
		System.out.println(input);
		System.out.println("There are "+ subStrings.size() + " substrings.");
		System.out.println("There are "+ count + " loops executed.");
		for(String subStr:subStrings)
		{
			System.out.println(subStr);
		}
	}

}
