package DataStructures;

public class LinkedList {
	private Node head;
	private Node tail;
	private int length;

	private class Node {
		private int value;
		private Node next;

		Node(int value) {
			this.value = value;
		}
	}

	LinkedList(int value) {
		Node newNode = new Node(value);
		head = newNode;
		tail = newNode;
		length = 1;
	}

	public Node get(int index) {
		if (index >= length || index < 0) {
			return null;
		} else if (index == 0) {
			return head;
		} else if (index == length - 1) {
			return tail;
		}

		Node temp = head;
		int currentIndex = 0;
		while (currentIndex < index) {
			temp = temp.next;
			currentIndex++;
		}
		return temp;
	}

	public int getLength() {
		return length;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
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
		}

		return temp;
	}

	public Node removeLast() {
		if (length == 0) {
			return null;
		}
		Node secondLast = get(length - 2);
		Node temp = tail;

		tail = secondLast;
		if (secondLast == null) {
			head = null;
		} else {
			secondLast.next = null;
		}
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
			Node before = get(index - 1);
			Node temp = before.next;

			before.next = temp.next;
			temp.next = null;

			length--;

			return temp;
		}
	}

	public void reverse() {
		if (length <= 1) {
			return;
		}
		Node before = head;
		Node current = head.next;
		Node temp;
		before.next = null;
		tail = before;

		while (current != null) {

			temp = current.next;
			current.next = before;
			before = current;
			current = temp;
		}

		head = before;
	}

	public Node findMiddleNode() {
		// Tortoise and hare algorithm, cannot use length
		if (head == null) {
			return null;
		}
		Node tortoise = head;
		Node hare = head.next;
		while (hare != null) {
			
			tortoise = tortoise.next;
			if (hare != null && hare.next != null) {
				hare = hare.next.next;
			} else {
				hare = null;
			}
		}
		
		return tortoise;
	}

	public boolean hasLoop() {
		if (length <= 1) {
			return false;
		}
		// Use tortoise and hare algorithm
		Node tortoise = head;
		Node hare = head.next;

		while (hare != null) {
			if (tortoise == hare) {
				return true;
			}
			
			tortoise = tortoise.next;
			hare = hare.next != null ? hare.next.next : null;
		}
		
		return false;
	}

	public Node findKthFromEnd(int k) {
		if (k <= 0 || k > length) {
			return null;
		}
		int kIndex = length - k;
		return get(kIndex);
	}

	public boolean reverseBetween(int startIndex, int endIndex) {
		if ((endIndex - startIndex) <= 0) {
			return false;
		}
		Node beforeStart = get(startIndex - 1);
		Node start = beforeStart == null ? head : beforeStart.next;

		Node prior = start;
		Node current = prior.next;
		int currentIndex = startIndex + 1;
		Node temp;

		// Reverse 'next' pointer within the selected range
		while (currentIndex <= endIndex && current != null) {
			temp = current.next;

			current.next = prior;

			// Move prior and current pointers
			prior = current;
			current = temp;
			currentIndex++;
		}

		// Link nodes immediately outside the selected range with the reversed nodes
		// Reassign head and tail pointers if selected range is previously the head
		// and/or tail
		Node end = prior;
		Node afterEnd = current;
		if (beforeStart == null) {
			head = end;
		} else {
			beforeStart.next = end;
		}

		if (afterEnd == null) {
			tail = start;
			start.next = null;
		} else {
			start.next = afterEnd;
		}

		return true;
	}

	public void partitionList(int x) {
		LinkedList lt = null;
		LinkedList gte = null;

		while (length > 0) {
			Node temp = removeFirst();
			if (temp.value < x) {
				if (lt != null) {
					lt.append(temp.value);
				} else {
					lt = new LinkedList(temp.value);
				}
			} else if (temp.value >= x) {
				if (gte != null) {
					gte.append(temp.value);
				} else {
					gte = new LinkedList(temp.value);
				}
			}
		}

		while (lt != null && lt.getLength() > 0) {
			append(lt.removeFirst().value);
		}

		while (gte != null && gte.getLength() > 0) {
			append(gte.removeFirst().value);
		}
	}

	public void removeDuplicates() {

		Node outer = head;
		int index = 0;
		while (outer != null) {

			Node inner = outer.next;
			int innerIndex = index + 1;

			while (inner != null) {
				if (outer.value == inner.value) {
					inner = inner.next;
					remove(innerIndex);
				} else {
					innerIndex++;
					inner = inner.next;
				}
			}

			outer = outer.next;
			index++;
		}
	}

	public void print() {
		System.out.println("Printing linked list");
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
