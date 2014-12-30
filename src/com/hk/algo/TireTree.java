package com.hk.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TireTree {

	//private static String path = "d:/myprojects/algo/resource/word1510_big.txt";
	private static String path = "d:/myprojects/algo/resource/word1510.txt";
	private boolean initSucc = false;
	private Node treeRoot = new Node(null,"");
	private long wordCount = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TireTree tree = new TireTree();
		tree.init(path);
		tree.find("wise");
		tree.find("airforce");
		tree.find("air force");
		tree.find("abus");
		
		try {
			Thread.sleep(3600*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(String path)
	{
		if(path==null)
			return;
		File file = new File(path);
		if(!file.exists())
			return;
		try {
			FileReader freader = new FileReader(file);
			BufferedReader reader = new BufferedReader(freader);
			String word = null;
			long count =0;
			while ((word = reader.readLine()) != null) {
				count ++;
				build(word);
            }
			System.out.println("Initial Tier tree finished. There are " + count + " lines in this file.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		initSucc = true;
	}
	
	private void build(String word)
	{
		char[] charArray = word.toCharArray();
		try {
			findWord(charArray,0,treeRoot,true);
		} catch (WordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long find(String word)
	{

		char[] charArray = word.toCharArray();
		try {
			findWord(charArray,0,treeRoot,false);
		} catch (WordNotFoundException e) {
			// TODO Auto-generated catch block
			wordCount = 0;
		}
		System.out.println("Word " + word + " is " + ((wordCount == 0)?"not found":"found") + " in Tier tree.\n"
				+ "Occurance is " + wordCount + "." );
		return wordCount;
	}
	
	private void findWord(char[] array, int curIndex,Node curNode,boolean insert) throws WordNotFoundException
	{
		Character curChar = new Character(array[curIndex]);
		Node child = null;

		if((child=curNode.children.get(curChar))==null)
		{
			if(insert)
			{
				child = curNode.addChild(curChar);
			}
			else
			{
				throw new WordNotFoundException();
			}

		}
		
		if(curIndex != array.length-1)
		{
			//Not reach string end
			if(child != null)
			{	//child is the new created (insert), or is found successfully
				findWord(array,++curIndex,child,insert);
			}
			else
			{
				throw new WordNotFoundException();
			}
		}
		else
		{	//Reach string end
			if(child != null)
			{
				//child is the new created (insert), or is found successfully
				if(insert)
				{
					child.count++;
				}
				else
				{
					wordCount = child.count;
				}
			}
			else
			{
				throw new WordNotFoundException();
			}
		}
	}
	
	class Node{
		String val = null;
		Character c = null;
		Map<Character,Node> children = new HashMap<Character,Node>();
		long count = 0;
		public Node(Character c,String parent) {
			super();
			this.c = c;
			this.val = parent + c;
			//count++;
		}
		
		public Node addChild(Character c)
		{
			Node child = new Node(c,this.val);
			children.put(c, child);
			return child;
		}	
	}
	
	class WordNotFoundException extends Exception
	{
		
	}
	
	
}
