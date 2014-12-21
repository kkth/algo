package com.hk;

import java.util.ArrayList;
import java.util.List;

public class GetPermu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("begin");
		GetPermu program = new GetPermu();
		String s = "1234ab";
		List<String> ret = program.cal(s);
		System.out.println("There are " + ret.size() + " permutation of "+ s);
		for(String sret :ret)
		{
			System.out.println(sret);
		}
	}
	
	List<String> cal(String s)
	{
		if(s == null)
		{
			return null;
		}
		
		List<String> ret = new ArrayList<String>();
		
		if(s.length()==1)
		{
			ret.add(s);
			return ret;
		}
		List<String> pred =  cal(s.substring(0,s.length()-1));
		String curChar = s.substring(s.length()-1);
		
		
		
		
		for(String spred :pred)
		{
			int spredlen = spred.length();
			ret.add(curChar + spred);
			
			for(int pos=1;pos<spredlen;pos++)
			{
				String s1 = spred.substring(0, pos);
				String s2 = spred.substring(pos,spredlen);
				ret.add(s1 + curChar + s2);
			}
			
			ret.add(spred+curChar);
		}
		
		return ret;
			
			
	}
	
	

}
