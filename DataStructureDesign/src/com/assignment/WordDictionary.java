package com.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordDictionary {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		// File path is passed as parameter
		File file = new File("/Users/singh/Desktop/list.txt");

		// Creating an object of BuffferedReader class
		BufferedReader br = new BufferedReader(new FileReader(file));

		// Declaring a string variable
		String st;

		// there is character in a string
		while ((st = br.readLine()) != null)

		// inserting words from file to Trie Nodes
		trie.insertWord(st);

		System.out.println("Dictionary initialized");
		
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println("Enter the Word which you want to search in Dictionary");
			String word=sc.next();
			System.out.println(trie.advanceSearch(word));
			System.out.println("Exit? yes or no");
		    String input = sc.next();
		    if (input.equals("yes")) {
		    	System.out.println("Thank You!!");
		        break;
		    }
		}
		while (true) ;
		
	}

}

class TrieNode {
	Map<Character, TrieNode> children;
	boolean endOfWord;

	TrieNode() {
		children = new HashMap<Character, TrieNode>();
		endOfWord = false;
	}
}

class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insertWord(String str) {

		TrieNode current = root;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			TrieNode node = current.children.get(c);
			if (node == null) {
				node = new TrieNode();
				current.children.put(c, node);
			}
			current = node;
		}
		current.endOfWord = true;
	}

	public List<String> advanceSearch(String initial) {
		List<String> autoCompWords = new ArrayList<String>();

		TrieNode currentNode = root;

		for (int i = 0; i < initial.length(); i++) {
			currentNode = currentNode.children.get(initial.charAt(i));
			if (currentNode == null)
				return autoCompWords;
		}

		searchWords(currentNode, autoCompWords, initial);
		return autoCompWords;
	}

	private void searchWords(TrieNode currentNode, List<String> autoCompWords, String word) {

		if (currentNode == null)
			return;

		if (currentNode.endOfWord) {
			autoCompWords.add(word);
		}

		Map<Character, TrieNode> map = currentNode.children;
		for (Character c : map.keySet()) {
			searchWords(map.get(c), autoCompWords, word + String.valueOf(c));
		}

	}

}
