package DataStructures;

public class QueueFromStack<T> {
	StackArrayList<T> stack1;
	StackArrayList<T> stack2;

	QueueFromStack() {
		stack1 = new StackArrayList<T>();
		stack2 = new StackArrayList<T>();
	}

	public void enqueue(T value) {
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		stack2.push(value);
		while(!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
	}

	public T dequeue() {
		if (stack1.isEmpty()) {
			return null;
		}
		
		return stack1.pop();
	}

	public int getLength() {
		return stack.size();
	}
}
