import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashTable<K, V> {
	int capacity;
	int size;
	Set<K> keySet;
	List<K> keys;
	List<V> values;
	public List<Integer> histogram;
	
	public MyHashTable(int capacity) {
		this.capacity = capacity;
		size = 0;
		keys = new ArrayList<K>();
		keySet = new HashSet<K>();
		values = new ArrayList<V>();
		histogram = new ArrayList<Integer>();
		for (int i = 0; i < capacity; i++) {
			keys.add(null);
			values.add(null);
		}
	} 
	
	public void put(K searchKey, V newValue) {
		int index = hash(searchKey);
		int i = index;
		if (size < capacity) {
			/* if (keys.get(index) == null) {
				keys.add(index, searchKey);
				values.add(index, newValue);
			}
			else { */
				while (keys.get(i) != null) {
					if (keys.get(i).equals(searchKey)) {
						break;
					}
					i = (i + 1) % capacity;
				}
				keys.remove(i);
				keys.add(i, searchKey);
				values.remove(i);
				values.add(i, newValue);
			//}
			size++;
			keySet.add(searchKey);
		}
	}
	
	public V get(K searchKey) {
		int index = hash(searchKey);
		int i = index;
		V val;
		if (keys.get(index) != null) {
			while (!keys.get(i).equals(searchKey)) {
				i = (i + 1) % capacity;
			}
		}
		val = values.get(i);
		return val;	
	}
	
	public void putt(K searchKey, V newValue) {
		int index = hash(searchKey);
		int i = index;
		int step = 0;
		if (size < capacity) {
			while (keys.get(i) != null) {
				if (keys.get(i).equals(searchKey)) {
					break;
				}
				step++;
				i = (int) ((index + Math.pow(step, 2)) % capacity);
			}
			keys.remove(i);
			keys.add(i, searchKey);
			values.remove(i);
			values.add(i, newValue);
			
			size++;
			keySet.add(searchKey);
		}
	}
	
	public V gett(K searchKey) {
		int index = hash(searchKey);
		int i = index;
		int step = 0;
		V val;
		if (keys.get(index) != null) {
			while (!keys.get(i).equals(searchKey)) {
				step++;
				i = (int) ((index + Math.pow(step, 2)) % capacity);
			}
		}
		val = values.get(i);
		return val;	
	}
	
	public boolean containsKey(K searchKey) {
		return keySet.contains(searchKey);
	}
	
	public int size() {
		return size;
	}
	
	private int hash(K key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	public Set<K> keySet() {
		return keySet;
	}
	
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder(256);
		sb.append("[");
		for (int i = 0; i < keys.size(); i++) {
			if (i < keys.size()-1 && keys.get(i) != null) {
				sb.append(keys.get(i) + "= " + values.get(i) + ", ");
			}
			else if (i == keys.size()-1 && keys.get(i) == null) {
				sb.append("");
			}
			else if (i == keys.size()-1 && keys.get(i) != null)
				sb.append(keys.get(i) + "= " + values.get(i) + "");
		}
		/*if (size >= 1) {
			sb.append(keys.get(size-1) + "= " + values.get(size-1));
		}
		else 
			sb.append(""); */
		sb.append("]");
		return sb.toString(); 
	}
	
	void stats() {
		StringBuilder sb = new StringBuilder(256);
		double fillPercent = size / capacity;
		sb.append("Number of Entries: " + size);
		sb.append("Number of Buckets: " + capacity);
		sb.append("Histogram of Probes: [");
		for (int i = 0; i < histogram.size(); i++) {
			sb.append(histogram.get(i));
		}
		sb.append("Fill Percentage: " + fillPercent + "%");
		sb.append("Max Linear Prob: ");
		sb.append("Average Linear Prob: ");
	}

}

