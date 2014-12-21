package com.hk;

import java.util.Stack;

public class ReverseKNodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseKNodes rev = new ReverseKNodes();
		Node head = rev.buildLinkedList(new String[]{"1","2","3","4","5","6","7","8","9","a","b","c"});
		rev.printLinkedList(head);
		Node newHead =rev.reverse(head, 3);
		rev.printLinkedList(newHead);
	}
	
	private Node buildLinkedList(String[] strs)
	{
		if(strs == null || strs.length == 0)
		{
			return null;
		}
		
		Node head = new Node();
		Node cur = head;
		
		for(String str:strs)
		{
			if(head.getVal() == null)
			{
				head.setVal(str);
				continue;
			}
			else
			{
				Node newNode = new Node();
				newNode.setVal(str);
				cur.setNext(newNode);
				cur = newNode;
			}
		}
		

		
		return head;
	}
	
	private void printLinkedList(Node head)
	{
		if(head == null)
		{
			System.out.println("Null linked list");
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			Node cur = head;
			do
			{
				sb.append(cur.getVal());
				sb.append("->");
				cur = cur.getNext();
			}while(cur != null);
			sb.append("null");
			
			System.out.println(sb.toString());
		}
		
		
	}
	
	private Node reverse(Node head,int step)
	{
		Stack<Node> stack = new Stack<Node>();
		if(head ==null)
		{
			return head;
		}
		
		Node cur = head;
		Node newHead = null;
		Node prevTail = null;
		do
		{		
			int count =0;
			do
			{
				stack.push(cur);
				count ++;
				cur = cur.next;
			}while(cur!=null && count <step);
	
			Node nextSectionHead = cur;
			
			
			Node revHead = stack.pop();
			if(newHead == null)
			{
				newHead = revHead;
			}
			cur = revHead;
			while(!stack.empty())
			{
				cur.next = stack.pop();
				cur = cur.next;
			}

			
			if(prevTail !=null)
			{
				prevTail.next = revHead;
			}
			//Move previous tail to current tail 
			
			prevTail = cur;
			
			cur = nextSectionHead;
			if(cur ==null)
			{
				prevTail.next = null;
			}
		}while(cur!= null);
		
		return newHead;
	}
	
	
	
	
	class Node{
		String val = null;
		Node next = null;
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
		
	}
	

}
