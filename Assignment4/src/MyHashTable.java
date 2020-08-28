
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashTable<K, V> {
	int capacity;
	Set<K> keySet;
	List<K> keys;
	List<V> values;
	List<Integer> histogram;
	Set<K> keySet2;
	List<K> keys2;
	List<V> values2;
	public List<Integer> histogram2;
	
	public MyHashTable(int capacity) {
		this.capacity = capacity;
		keys = new ArrayList<K>();
		keySet = new HashSet<K>();
		values = new ArrayList<V>();
		histogram = new ArrayList<Integer>();
		keys2 = new ArrayList<K>();
		keySet2 = new HashSet<K>();
		values2 = new ArrayList<V>();
		histogram2 = new ArrayList<Integer>();
		for (int i = 0; i < capacity; i++) {
			keys.add(null);
			values.add(null);
			keys2.add(null);
			values2.add(null);
			histogram.add(0);
			histogram2.add(0);
		}
	} 
	
	public void put(K searchKey, V newValue) {
		int index = hash(searchKey);
		int i = index;
		int probes = 0;
		if (keySet.size() < capacity) {
			while (keys.get(i) != null) {
				if (keys.get(i).equals(searchKey)) {
					break;
				}
				i = (i + 1) % capacity;
				probes++;
			}
			if (!this.containsKey(searchKey)) {
				histogram.set(probes, histogram.get(probes) + 1);
			}
			keys.set(i, searchKey);
			values.set(i, newValue);
			keySet.add(searchKey);
		}
	}
	
	public V get(K searchKey) {
		int index = hash(searchKey);
		int i = index;
		V val;
		if (searchKey == null) {
			val = null;
		}
		while (!keys.get(i).equals(searchKey)) {
			i = (i + 1) % capacity;
		}
		val = values.get(i); 
		return val;
	}
	
	public void putt(K searchKey, V newValue) {
		int index = hash(searchKey);
		int i = index;
		int step = 0;
		int probes = 0;
		if (keySet2.size() < capacity) {
			while (keys2.get(i) != null) {
				if (keys2.get(i).equals(searchKey)) {
					break;
				}
				step++;
				i = (int) ((index + (int)Math.pow(step, 2)) % capacity);
				probes++;
			}
			if (!this.containsKey2(searchKey)) {
				histogram2.set(probes, histogram2.get(probes) + 1);
			}
			keys2.set(i, searchKey);
			values2.set(i, newValue);
			keySet2.add(searchKey);
		}
	}
	
	public V gett(K searchKey) {
		if (!this.containsKey2(searchKey)) {
			return null;
		}
		int index = hash(searchKey);
		int i = index;
		int step = 0;
		V val;
		while (!keys2.get(i).equals(searchKey)) {
			step++;
			i = (int) ((index + Math.pow(step, 2)) % capacity);
		}
		val = values2.get(i);
		return val;	
	}
	
	public boolean containsKey(K searchKey) {
		return keySet.contains(searchKey);
	}
	
	public boolean containsKey2(K searchKey) {
		return keySet2.contains(searchKey);
	}
	
	public int size() {
		return keySet.size();
	}
	
	public int size2() {
		return keySet2.size();
	}
	
	private int hash(K key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	public Set<K> keySet() {
		return keySet;
	}
	
	public Set<K> keySet2() {
		return keySet2;
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
		String temp = "";
		sb.append("Number of Entries: " + this.size() + "\n");
		sb.append("Number of Buckets: " + capacity + "\n");
		sb.append("Histogram of Probes: [");
		temp += "Histogram of Probes: [";
		int maxProbe = histogram.get(0);
		int size = 0;
		int total = 0;
		for (int i = 0; i < histogram.size(); i++) {
			if (maxProbe >= histogram.get(i) && histogram.get(i) > 0) {
				maxProbe = histogram.get(i);
				size = i;
			}	
		}
		float fillPercent = ((float)this.size() / (float)(capacity)) * 100;
		for (int i = 0; i <= size; i++) {
			if (i < size) {
				sb.append(histogram.get(i) + ",");
			}
			else {
				sb.append(histogram.get(i));
			}
			temp += histogram.get(i);
			if (temp.length() > 0 && temp.length() % 25 == 0 && i != size) {
				sb.append("\n");
			}
			total += histogram.get(i) * i;
		}
		double average = (double)total / (double)this.size();
		sb.append("]" + "\n");
		sb.append("Fill Percentage: " + fillPercent + "%" + "\n");
		sb.append("Max Linear Probe: " + size + "\n");
		sb.append("Average Linear Prob: " + average + "\n");
		System.out.println(sb);
	}
	
	void stats2() {
		StringBuilder sb = new StringBuilder(256);
		String temp = "";
		sb.append("Number of Entries: " + this.size2() + "\n");
		sb.append("Number of Buckets: " + capacity + "\n");
		sb.append("Histogram of Probes: [");
		temp += "Histogram of Probes: [";
		int maxProbe = histogram2.get(0);
		float fillPercent = ((float)this.size2() / (float)(capacity)) * 100;
		int size = 0;
		int total = 0;
		for (int i = 0; i < histogram2.size(); i++) {
			if (maxProbe >= histogram2.get(i) && histogram2.get(i) > 0) {
				maxProbe = histogram2.get(i);
				size = i;
			}	
		}
		for (int i = 0; i <= size; i++) {
			if (i < size) {
				sb.append(histogram2.get(i) + ",");
			}
			else {
				sb.append(histogram2.get(i));
			}
			temp += histogram2.get(i);
			if (temp.length() > 0 && temp.length() % 25 == 0 && i != size) {
				sb.append("\n");
			}
			total += histogram2.get(i)*i;
		}
		double average = (double)total / (double)this.size2();
		sb.append("]" + "\n");
		sb.append("Fill Percentage: " + fillPercent + "%" + "\n");
		sb.append("Max Linear Probe: " + size + "\n");
		sb.append("Average Linear Prob: " + average + "\n");
		System.out.println(sb);
	}

}


