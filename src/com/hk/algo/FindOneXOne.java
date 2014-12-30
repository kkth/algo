package com.hk.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Map<Integer,List<SubStringIndex> > subStringMap = new HashMap<Integer,List<SubStringIndex> >();
	private int lastOneIndex = -1;
	private int count2 = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindOneXOne find = new FindOneXOne();
		find.init();
		find.cal1();
		find.cal2();
	}
	
	public void init()
	{
		input = "001001010";
		//input ="111111011";
		inputArray = input.toCharArray();
	}
	
	
	public void cal1()
	{
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
	
	
	//////////////////////////////////////////
	class SubStringIndex
	{
		int beginInclude =-1;
		int endExclude = -1;
	}
	
	public void cal2()
	{
		System.out.println("Cal2======");
		for(int i =0;i<inputArray.length;i++)
		{
			calBasedBefore(i);
		}
		
		if(!subStringMap.isEmpty())
		{
			int count = 0;
			Collection<List<SubStringIndex>> subStrings = subStringMap.values();
			for(List<SubStringIndex> list: subStrings)
			{
				for(SubStringIndex index:list)
				{
					String subString = new String(inputArray,index.beginInclude,index.endExclude-index.beginInclude);
					System.out.println(subString);
					count ++;
				}
			}
			
			System.out.println("There are "+ count + " substrings.");
		}
	}
	
	public void calBasedBefore(int index)
	{

			
		if(inputArray[index] == '0')
		{	
			return;
		}
		else
		{
			List<SubStringIndex> newIndexList = new ArrayList<SubStringIndex>();
			
			if(lastOneIndex==-1)
			{
				SubStringIndex newIndex = new SubStringIndex();
				newIndex.beginInclude = index;
				newIndex.endExclude = index+1;
				newIndexList.add(newIndex);
			}
			else
			{
				List<SubStringIndex> subStrings = subStringMap.get(new Integer(lastOneIndex));
	
				for(SubStringIndex theIndex : subStrings)
				{
					SubStringIndex newIndex1 = new SubStringIndex();
					newIndex1.beginInclude = theIndex.beginInclude;
					newIndex1.endExclude = index+1;
					newIndexList.add(newIndex1);
					
					SubStringIndex newIndex2 = new SubStringIndex();
					newIndex2.beginInclude = theIndex.endExclude-1;
					newIndex2.endExclude = index+1;
					newIndexList.add(newIndex2);
					
				}
			}
			
			subStringMap.put(new Integer(index), newIndexList);
			lastOneIndex = index;
			
		}
		
			
	}

}
