package com.hk.algo;


import java.util.Stack;

public class ExcelHeaders {

	private final int ALPHA = 26;
	private int base = '@';
	
	private Stack<String> stack = new Stack<String>();
	public static void main(String[] args)
	{
		System.out.println("ExcelHeaders class main begin");
		long val = 53;
		ExcelHeaders headers = new ExcelHeaders();
		headers.convertToHead(val);
		StringBuffer sb = new StringBuffer();
		while(!headers.stack.empty())
		{
			sb.append(headers.stack.pop());
		}
		System.out.println(sb.toString());
	}
	
	class Result
	{
		public long quotient = 0;
		public long remainder = 0;
		boolean valid = false;
	}
	
	private void convertToHead(long val)
	{
		Result result = cal(val);
		if(result.valid)
		{
			stack.push(convert(result.remainder));
			if(result.quotient >=0 && result.quotient<=ALPHA)
			{
				stack.push(convert(result.quotient));
			}
			else
			{
				convertToHead(result.quotient);
			}
		}
	}
	
	private Result cal(long input)
	{
		Result ret = new Result();
		if(input == 0)
			return ret;
		if(input%ALPHA !=0)
		{
			ret.quotient = input/ALPHA;
			ret.remainder = input%ALPHA;
		}
		else
		{
			ret.quotient = input/ALPHA-1;
			ret.remainder = ALPHA;
		}
		ret.valid = true;
		return ret;
	}
	
	private String convert(long val)
	{
		String ret = "";
		if(val >=1 && val<=ALPHA)
		{
			char c = (char)(base + val); 
			ret = String.valueOf(c);
		}
		return ret;
	}
	
}
