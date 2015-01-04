package com.hk.algo;

import java.util.HashMap;
import java.util.Map;

public class Coin2 {

	enum NodeState{
		NA,
		TBD,
		GET
	};
	class dpNode{
		int sumVal = -1;//Sum of coins' value, including current node 
		int coinVal = -1;//Value of coin used at current node
		int coinCount = -1;//How many coins are used until (including) current node 
		dpNode prevNode = null;
		NodeState state = NodeState.TBD;
	}
	private static int[] COINS = {2,5,10,20,50,100};
	private static int COUNT = 15580;
	private Map<Integer,dpNode> nodes= new HashMap<Integer,dpNode>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coin2 program = new Coin2();
		program.init();
		dpNode node = program.cal(COUNT);
		if(node.state == NodeState.NA)
		{
			System.out.println("Value " + COUNT + " cannot be aggregrated by coins " + COINS);
		}
		else
		{
			System.out.println("Value " + COUNT + " can be aggregrated by " + node.coinCount + " coins.\n" +
					"One of the layout is\n");
			while(node != null)
			{
				System.out.println("Coin value : " + node.coinVal + ". Count: 1");
				node = node.prevNode;
			}
		}
		
	}
	
	private void init()
	{
		
	}
	
	private dpNode cal(int maxVal)
	{
		dpNode curNode = null;
		for(int val =1;val<=maxVal;val++)
		{
			curNode = new dpNode();
			dpNode minNode = null;
		
			boolean singleMatch = false;
			for(int i: COINS)
			{
				int subVal = val-i;
				if(subVal>0)
				{
					dpNode node = nodes.get(subVal);
					if(node != null)
					{
						if(node.state != NodeState.NA)
						{
							if(minNode ==null)
							{
								minNode = node;
							}
							else
							{
								if(minNode.coinCount>node.coinCount)
								{
									minNode = node;
								}
							}
						}
					}
				}
				else if(subVal == 0)
				{
					curNode = new dpNode();
					curNode.sumVal = val;
					curNode.prevNode = null;
					curNode.coinVal = val;
					curNode.coinCount = 1;
					curNode.state = NodeState.GET;
					nodes.put(val, curNode);
					singleMatch = true;
				}
	
			}
			
			if(!singleMatch)
			{
				if(minNode != null)
				{
					curNode.sumVal = val;
					curNode.prevNode = minNode;
					curNode.coinVal = val - minNode.sumVal;
					curNode.coinCount = minNode.coinCount +1;
					curNode.state = NodeState.GET;
				}
				else
				{
					curNode.state = NodeState.NA;
				}
				nodes.put(val, curNode);
			}
		}

		return curNode;
	}
	
	

}
