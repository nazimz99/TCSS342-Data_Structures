
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyPriorityQueue<T extends Comparable<? super T>> {
	
	public List<T> elements;
	private int size;
	
	public MyPriorityQueue() {
		elements = new ArrayList<T>();
		size = 0;
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else 
			return false;
	}
	
	public int size() {
		return this.size;
	}
	
	public int leftChild(int index) {
		return 2 * index + 1;
	}
	
	public int rightChild(int index) {
		return 2 * index + 2;
	}
	
	public int parent(int index) {
		return (int)Math.floor((index - 1)/2);
	}
	
	public boolean add(T item) {
		if (item == null) {
			throw new NullPointerException();
		}
		elements.add(item);
		size++;
		bubble(elements.size()-1);
		Collections.sort(elements);
		return true;
	}
	
 	public void bubble(int index) {
 		if (index == 0 || elements.get(parent(index)).compareTo(elements.get(index)) < 0) {
 			return;
 		}
		//if (index > 0 && elements.get(index).compareTo(elements.get(parent(index))) < 0) {
			T parent = elements.get(parent(index));
			elements.set(parent(index), elements.get(index));
			elements.set(index, parent);
			index = parent(index);
			bubble(index);	
		//}             
	}
 	
	
	public boolean isSorted() {
		boolean sorted = true;
		for (int i = 1; i < size; i++) {
			if (elements.get(i-1).compareTo(elements.get(i)) > 0) {
				sorted = false;
				break;
			}
		}
		return sorted;
	}
    
    public void swap(int i, int j) {
    	T node1 = elements.get(i);
    	T node2 = elements.get(j);
    	elements.set(j, node1);
    	elements.set(j, node2);
    }
	
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		else 
			return elements.get(0);
	}
	
	/*public void sink(int index) {
		if (index == elements.size()-1) {
			return;
		}
		int left = leftChild(index);
		int right = rightChild(index);
		int minimum;
		
		if (right > elements.size()) {
			minimum = left;
		}
		else {
			if (elements.get(left).compareTo(elements.get(right)) < 0) {
				minimum = left;
			}
			else {
				minimum = right;
			}
			//minimum = elements.get(left).compareTo(elements.get(right)) < 0 ? left : right;
		}
		
		if (elements.get(index).compareTo(elements.get(minimum)) < 0) {
			return;
		}
		T child = elements.get(minimum);
		elements.set(minimum, elements.get(index));
		elements.set(index, child);
		sink(minimum);
		
	} */
	
	public T poll() {
		if (isEmpty()) {
			return null;
		}
		T minNode = elements.get(0);
		elements.remove(minNode);
		size--;
		return minNode;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(256);
		sb.append("[");
		for (int i = 0; i < elements.size(); i++) {
			if (i < elements.size()-1) {
				sb.append(elements.get(i) + ", ");
			}
			else 
				sb.append(elements.get(i));
		}
		sb.append("]");
		return sb.toString();
	}
	
}
