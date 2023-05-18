package DataStructures;

import java.util.ArrayList;

public class StackArrayList<T> {
	ArrayList<T> stackList;

	StackArrayList() {
		stackList = new ArrayList<T>();
	}

	public boolean isEmpty() {
		return stackList.size() == 0;
	}

	public void push(T value) {
		stackList.add(value);
	}

	public T pop() {
		if (isEmpty()) {
			return null;
		}

		return stackList.remove(stackList.size() - 1);
	}

	public int size() {
		return stackList.size();
	}

	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return stackList.get(stackList.size() - 1);
	}

	public T getBottom() {
		if (isEmpty()) {
			return null;
		}
		return stackList.get(0);
	}
	
	public void print() {
		for(int i=0; i<size(); i++) {
			System.out.println(stackList.get(i));
		}
	}

}
