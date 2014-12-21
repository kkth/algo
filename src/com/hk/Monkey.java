package com.hk;

public class Monkey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(cal(0));
	}
	
	public static int cal(int index)
	{
		if(index==9)
		{
			return 1;
		}
		else
		{
			return (cal(index +1)+1)*2;
		}
	}

}
