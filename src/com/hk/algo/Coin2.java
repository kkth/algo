package com.hk.algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	private static int[] COINS = {2,5,20,1004,58,19};
	private static int COUNT = 155802;
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
			Map<Integer,Integer> coinsMap = new HashMap<Integer,Integer>();
			while(node != null)
			{
				//System.out.println("Coin value : " + node.coinVal + ". Count: 1");
				//node = node.prevNode;
				Integer coinCount = coinsMap.get(node.coinVal);
				if(coinCount ==null)
				{
					coinsMap.put(node.coinVal, 1);
				}
				else
				{
					coinsMap.put(node.coinVal, coinCount+1);
				}
				node = node.prevNode;
			}
			
			Set<Integer> keysets = coinsMap.keySet();
			TreeSet<Integer> sortedset = new TreeSet<Integer>(keysets);
			for(Integer val:sortedset)
			{
				if(val != 0)
				{
					System.out.println("Coin value : " + val + ". Count: " + coinsMap.get(val) );
				}
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
