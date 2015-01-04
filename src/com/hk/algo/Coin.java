package com.hk.algo;

import java.util.HashMap;
import java.util.Map;

public class Coin {

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
	private static int[] COINS = {2,9,10};
	private static int COUNT = 15;
	private Map<Integer,dpNode> nodes= new HashMap<Integer,dpNode>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coin program = new Coin();
		program.init();
		dpNode node = program.cal(COUNT);
		if(node.state == NodeState.NA)
		{
			System.out.println("Value " + COUNT + " cannot be aggregrated by coins " + COINS);
		}
		else
		{
			System.out.println("Value " + COUNT + " can be aggragrated by " + node.coinCount + " coins.\n" +
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
	
	private dpNode cal(int val)
	{
		dpNode curNode = new dpNode();
		dpNode minNode = null;
		for(int i: COINS)
		{
			int subVal = val-i;
			if(subVal>0)
			{
				dpNode node = nodes.get(val-1);
				if(node == null)
				{
					node = cal(subVal);
				}
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
			else if(subVal ==0)
			{
				curNode = new dpNode();
				curNode.sumVal = val;
				curNode.prevNode = null;
				curNode.coinVal = val;
				curNode.coinCount = 1;
				curNode.state = NodeState.GET;
				return curNode;
			}

		}
		
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
		
		return curNode;
	}
	
	

}
