package DataStructures;

import java.util.ArrayList;

public class HashTable {
	private final int size = 7;
	private Node[] dataMap;

	private class Node {
		private String key;
		private int value;
		private Node next;

		Node(String key, int value) {
			this.key = key;
			this.value = value;
		}

	}

	HashTable() {
		dataMap = new Node[size];
	}

	private int hash(String key) {
		int hash = 0;
		char[] keyChars = key.toCharArray();

		for (int i = 0; i < keyChars.length; i++) {
			int asciiValue = keyChars[i];
			hash = (hash + asciiValue * 23) % dataMap.length;
		}
		return hash;
	}

	public int get(String key) {
		int hash = hash(key);

		Node node = null;

		// start from head of linked list
		Node temp = dataMap[hash];
		while (temp != null) {
			if (temp.key != key) {
				temp = temp.next;
			} else {
				node = temp;
				break;
			}
		}

		// Not found key-value
		if (node == null) {
			return 0;
		}

		// Found key-value
		return node.value;
	}

	public void set(String key, int value) {
		Node newNode = new Node(key, value);
		int hash = hash(key);
		Node temp = dataMap[hash];
		// No node at this position before, insert new node as head
		if (temp == null) {
			dataMap[hash] = newNode;
		} else {
			// Traverse linked list to find last node or node with same key
			while (temp.next != null && temp.key != key) {
				temp = temp.next;
			}

			// If key already existed, replace value
			if (temp.key == key) {
				temp.value = value;
			}
			// Is last node of the linked list, append new node
			else {
				temp.next = newNode;
			}
		}
	}

	public ArrayList<String> keys() {
		ArrayList<String> allKeys = new ArrayList<>();

		for (int i = 0; i < dataMap.length; i++) {
			Node temp = dataMap[i];

			if (temp == null) {
				continue;
			}

			while (temp != null) {
				allKeys.add(temp.key);
				temp = temp.next;
			}
		}

		return allKeys;
	}
}
