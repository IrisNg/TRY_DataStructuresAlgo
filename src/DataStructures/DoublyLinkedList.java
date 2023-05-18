package DataStructures;

public class DoublyLinkedList {
	private Node head;
	private Node tail;
	private int length;

	private class Node {
		private int value;
		private Node next;
		private Node prev;

		Node(int value) {
			this.value = value;
		}
	}

	DoublyLinkedList(int value) {
		Node newNode = new Node(value);
		head = newNode;
		tail = newNode;
		length = 1;
	}

	public Node get(int index) {
		if (index >= length || index < 0) {
			return null;
		}

		int midLength = length / 2;
		Node temp = head;

		if (index <= midLength) {
			// start traversing from head

			temp = head;

			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}

		} else if (index > midLength) {
			// start traversing backwards from tail

			temp = tail;

			for (int i = length - 1; i > index; i--) {
				temp = temp.prev;
			}

		}

		return temp;
	}

	public boolean set(int index, int value) {
		Node node = get(index);

		if (node == null) {
			return false;
		}

		node.value = value;
		return true;
	}

	public void prepend(int value) {
		Node newNode = new Node(value);

		if (length == 0) {
			tail = newNode;
		} else {
			head.prev = newNode;
		}

		newNode.next = head;
		head = newNode;
		length++;
	}

	public void append(int value) {
		Node newNode = new Node(value);

		if (length == 0) {
			head = newNode;
		} else {
			tail.next = newNode;
		}
		newNode.prev = tail;
		tail = newNode;
		length++;
	}

	public Node removeFirst() {
		if (length == 0) {
			return null;
		}

		Node temp = head;
		head = temp.next;
		temp.next = null;

		length--;

		if (length == 0) {
			tail = null;
		} else {
			head.prev = null;
		}

		return temp;

	}

	public Node removeLast() {
		if (length == 0) {
			return null;
		}

		Node secondLast = tail.prev;
		Node temp = tail;
		tail = secondLast;

		if (secondLast == null) {
			head = null;
		} else {
			secondLast.next = null;
		}

		temp.prev = null;
		length--;

		return temp;
	}

	public boolean insert(int index, int value) {

		if (index > length || index < 0) {
			return false;
		}

		if (index == 0) {
			prepend(value);
		} else if (index == length) {
			append(value);
		} else {
			Node newNode = new Node(value);
			Node before = get(index - 1);
			Node after = before.next;

			before.next = newNode;
			after.prev = newNode;
			newNode.prev = before;
			newNode.next = after;

			length++;
		}

		return true;
	}

	public Node remove(int index) {
		if (index >= length || index < 0) {
			return null;
		}

		if (index == 0) {
			return removeFirst();
		} else if (index == length - 1) {
			return removeLast();
		} else {
			Node temp = get(index);
			Node before = temp.prev;
			Node after = temp.next;

			before.next = after;
			after.prev = before;

			temp.next = null;
			temp.prev = null;

			length--;
			return temp;
		}
	}

	public void swapFirstLast() {
		if (length <= 1) {
			return;
		}
		int headValue = head.value;
		int tailValue = tail.value;

		head.value = tailValue;
		tail.value = headValue;
	}

	public void reverse() {
		if (length <= 1) {
			return;
		}

		Node current = head;
		Node after = head.next;
		tail = current;

		while (current != null) {
			current.next = current.prev;
			current.prev = after;

			if (after == null) {
				head = current;
			}

			current = after;

			if (after != null) {
				after = after.next;
			}
		}

	}

	public boolean isPalindrome() {
		if (length <= 1) {
			return true;
		}
		Node nodeFromHead = head;
		Node nodeFromTail = tail;
		for (int i = 0; i < (length / 2); i++) {
			if (nodeFromHead.value != nodeFromTail.value) {
				return false;
			}

			nodeFromHead = nodeFromHead.next;
			nodeFromTail = nodeFromTail.prev;
		}
		return true;
	}

	public void swapPairs() {
		if (length <= 1) {
			return;
		}
		Node first = head;
		Node second = head.next;
		head = second;

		while (first != null && second != null) {

			Node beforeFirst = first.prev;
			Node afterSecond = second.next;

			second.prev = beforeFirst;
			second.next = first;
			if (beforeFirst != null) {
				beforeFirst.next = second;
			}
			first.prev = second;
			first.next = afterSecond;
			if (afterSecond != null) {
				afterSecond.prev = first;

				first = afterSecond;
				second = afterSecond.next;
			} else {
				first = null;
				second = null;
			}
		}
	}

	public void print() {
		System.out.println("Printing doubly linked list");
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
		System.out.println("Head: " + head.value);
		System.out.println("Tail: " + tail.value);
	}

	public void printLength() {
		System.out.println("length: " + length);
	}

}
