package DataStructures;

public class Queue {
	private Node first;
	private Node last;
	private int length;
	
	private class Node {
		private int value;
		private Node next;
		
		Node(int value) {
			this.value = value;
		}
	}
	
	Queue(int value) {
		Node newNode = new Node(value);
		first = newNode;
		last = newNode;
		length = 1;
	}
	
	public void enqueue(int value) {
		Node newNode = new Node(value);
		if (length == 0) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
		length++;
	}
	
	public Node dequeue() {
		if (length == 0) {
			return null;
		}
		Node temp = first;
		first = temp.next;
		temp.next = null;
		length--;
		if (length == 0) {
			last = null;
		}
		return temp;
 	}
	
	public void print() {
		System.out.println("Printing Queue from left: ");
		Node temp = first;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}
	
	public void printLength() {
		System.out.println("Length: " + length);
	}
}
