package DataStructures;

public class Stack {
	private Node top;
	private Node bottom;
	private int height;
	
	private class Node {
		private int value;
		private Node next;
		
		Node(int value) {
			this.value = value;
		}
	}
	
	Stack(int value) {
		Node newNode = new Node(value);
		top = newNode;
		bottom = newNode;
		height = 1;
	}
	
	public void push(int value) {
		Node newNode = new Node(value);
		if (height == 0) {
			bottom = newNode;
		}
		newNode.next = top;
		top = newNode;
		height++;
	}
	
	public Node pop() {
		if (height == 0) {
			return null;
		}
		
		Node temp = top;
		top = temp.next;
		temp.next = null;
		height--;
		
		if (height == 0) {
			bottom = null;
		}
		
		return temp;
	}
	
	public void print() {
		System.out.println("Printing Stack from top: ");
		Node temp = top;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}
	
	public void printLength() {
		System.out.println("Length: " + height);
	}
}
