package com.hk.algo;

public class LongestSubString {

	class PalinIndex
	{
		int begin = -1;//include
		int end = -1;//exclude
		int type = -1;//type :1 have intermediate character;0: without intermediate character
	}

	//private String input = "forgeeksbskeegfor";
	private String input = "ebe";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestSubString sub = new LongestSubString();
		sub.cal();

	}
	
	private void cal()
	{
		if(input == null||input.isEmpty())
			return;
		char[] charArray = input.toCharArray();
		PalinIndex palin = new PalinIndex();

		for(int index=0;index<charArray.length;index++)
		{
			if(palin.begin==-1)
			{
				palin.begin = 0;
				palin.end =1;
				continue;
			}
			
			if(palin.end == index)//Maybe there's palin without the intermediate character
			{
				if(palin.end- palin.begin==1)//Init status
				{
					if(charArray[palin.begin] == charArray[index])
					{
						palin.end++;
						continue;
					}
				}
				
				if(palin.begin-1 >=0)
				{
					if(charArray[palin.begin-1]== charArray[palin.end])//get same character, expand palin index
					{
						palin.begin--;
						palin.end++;
						continue;
					}
				}
			}
			else 
			{
				if(palin.end-palin.begin<=3)
				{
					if(index-2>=0)
					{
						if(charArray[index-2]==charArray[index])
						{
							palin.begin = index-2;
							palin.end = index+1;
							continue;
						}
					}
					
				}
				if(palin.end-palin.begin<=2)
				{
					if(index-1>=0)
					{
						if(charArray[index-1]==charArray[index])
						{
							palin.begin = index-1;
							palin.end = index+1;
							continue;
						}
					}
				}
			}
		}
		
		if(palin.end-palin.begin >1)
		{
			System.out.println("The longest palin in string " + input + " has length "+ (palin.end-palin.begin) +".\n" +
					"The begin and end index is " + palin.begin + " " + palin.end + ".\n"+
					"The substring is " + input.substring(palin.begin,palin.end));
		}
		else
		{
			System.out.println("No palin found in string "+ input);
		}
	}

}
