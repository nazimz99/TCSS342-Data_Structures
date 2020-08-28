package baronBurger;

public class MyStack<T> {
	private Node<T> topItem;
	private int size;
	
	private static class Node<T> {
		private T item;
		private Node<T> next;
		
		public Node(T item) {
			this.item = item;
		} 
		
		@Override
		public String toString() {
			return item.toString();
		}
	}
	
	public MyStack() {
		size = 0;
		topItem = null;
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else 
			return false;
	}
	
	public void push(T item) {
		Node<T> newItem = new Node<T>(item);
		newItem.next = topItem;
		topItem = newItem;
		size++;
	}
	
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		Node<T> prevItem = topItem;
		topItem = topItem.next;
		size--;
		return prevItem.item;
	}
	
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		else 
			return topItem.item;
	}	
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		MyStack<T> prevItems = new MyStack<T>();
		int prevSize = size;
		for (int i = 0; i < prevSize; i++) {
			if (i < prevSize - 1) {
				sb.append(this.peek() + ", ");
				prevItems.push(this.peek());
				this.pop();
			}
			else if (i == prevSize - 1) {
				sb.append(this.peek());
				prevItems.push(this.peek());
				this.pop();
			}
		}
		prevSize = prevItems.size();
		for (int i = 0; i < prevSize; i++) {
			this.push(prevItems.peek());
			prevItems.pop();
		}
		sb.append("]");
		return sb.toString();
	}

 }
