
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CodingTree {
	class Node implements Comparable<Node> {
	      int frequency;
	      String code;
	      String str;
	      Node left;
	      Node right;
	      
	      public Node(int frequency, Node left, Node right) {
	    	  this.str = null;
	    	  this.frequency = frequency;
	    	  this.left = left;
	    	  this.right = right;	    	  
	      }
	      
	      public Node (String str, int frequency) {
	         this.str = str;
	         this.frequency = frequency;
	         right = null;
	         left = null;
	      }
	   
	      boolean isLeaf() {
	         return this.right  == null && this.left == null;
	      }
	   
	      public String toString() {
	    	  String result = "";
	    	  result += str;
	    	  result += ", " + frequency;
	          return result; 
	      }


		public int compareTo(Node n) {
			if (this.frequency > n.frequency) {
				return 1;
			}
			else if (this.frequency < n.frequency) {
				return -1;
			}
			else 
				return 0;
		}
	}
	
	private Node root;
	public byte[] bits;
	public byte[] bits2;
	public StringBuilder binaryCodes;
	public StringBuilder binaryCodes2;
	public List<String> words;
	public List<String> words2;
	public MyHashTable<String, Integer> map;
	public MyHashTable<String, Integer> map2;
	public Map<String, String> codes;
	public MyHashTable<String, String> codes2;
	public PriorityQueue<Node> tree;
	public PriorityQueue<Node> tree2;
	public String fulltext;
	
	public CodingTree(String fulltext) {
		root = null;
		map = new MyHashTable<String, Integer>(32768);
		map2 = new MyHashTable<String, Integer>(32768);
		codes = new HashMap<String, String>(32768);
		codes2 = new MyHashTable<String, String>(32768);
		tree = new PriorityQueue<Node>();
		tree2 = new PriorityQueue<Node>();
		this.fulltext = fulltext;
		binaryCodes = new StringBuilder(1024);
		binaryCodes2 = new StringBuilder(1024);
		words = new ArrayList<String>();
		words2 = new ArrayList<String>();
		frequencyTree();
		constructTree();
		merge();
	}
	
	public MyHashTable<String, Integer> frequencyTree() {
		String word = "";
		String delim = "";
		StringBuilder sb = new StringBuilder(256);
		Set<Character> set = Stream.of('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','-','\'').collect(Collectors.toSet());
		for (int i = 0; i < fulltext.length(); i++) {
			delim = "";
			if (set.contains(fulltext.charAt(i))) {
				sb.append(fulltext.charAt(i));
			}
			else {
				if (sb.length() != 0) {
					word = sb.toString();
					sb.delete(0, sb.length());
					if (!map.containsKey(word)) {
						map.put(word, 1);
					}
					else {
						map.put(word, map.get(word) + 1);
					}
				}
				delim += fulltext.charAt(i);
				if (!map.containsKey(delim)) {
					map.put(delim, 1);
				}
				else {
					map.put(delim, map.get(delim) + 1);
				}
			}
		}
		return map;
	}
	
	public MyHashTable<String, Integer> frequencyTree2() {
		String word = "";
		String delim = "";
		StringBuilder sb = new StringBuilder(256);
		Set<Character> set = Stream.of('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','-','\'').collect(Collectors.toSet());
		for (int i = 0; i < fulltext.length(); i++) {
			delim = "";
			if (set.contains(fulltext.charAt(i))) {
				sb.append(fulltext.charAt(i));
			}
			else {
				if (sb.length() != 0) {
					word = sb.toString();
					sb.delete(0, sb.length());
					if (!map2.containsKey2(word)) {
						map2.putt(word, 1);
					}
					else {
						map2.putt(word, map2.gett(word) + 1);
					}
				}
				delim += fulltext.charAt(i);
				if (!map2.containsKey2(delim)) {
					map2.putt(delim, 1);
				}
				else {
					map2.putt(delim, map2.gett(delim) + 1);
				}
			}
		}
		return map2;
	}
	
	public void constructTree() {
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String str = itr.next();
			tree.add(new Node(str, map.get(str)));
		}   
	}
	
	public void constructTree2() {
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String str = itr.next();
			tree2.add(new Node(str, map.get(str)));
		}   
	}
 	
	public void merge() {
		while (tree.size() > 1) {
			Node n = this.tree.poll();
			Node n2 = this.tree.poll();
			int sum = n.frequency + n2.frequency;
			root = new Node(sum, n , n2);
			this.tree.add(root);
		}
	 }
	
	public void merge2() {
		while (tree2.size() > 1) {
			Node n = this.tree2.poll();
			Node n2 = this.tree2.poll();
			int sum = n.frequency + n2.frequency;
			root = new Node(sum, n , n2);
			this.tree2.add(root);
		}
	}
	 
	 public void traversal(Node n, String binaryCode) {
		 if (n.isLeaf() && n.str != null) {
			 codes.put(n.str, binaryCode);
			 return;
		 }
		 traversal(n.left, binaryCode + "0");
		 traversal(n.right, binaryCode + "1");
	 }	
	 
	 public void traversal2(Node n, String binaryCode) {
		 if (n.isLeaf() && n.str != null) {
			 codes2.putt(n.str, binaryCode);
			 return;
		 }
		 traversal2(n.left, binaryCode + "0");
		 traversal2(n.right, binaryCode + "1");
	 }
	 
	public void binaryCodes(String fulltext) {
		String word = "";
		String delim = "";
		StringBuilder sb = new StringBuilder(256);
		Set<Character> set = Stream.of('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','-','\'').collect(Collectors.toSet());
		for (int i = 0; i < fulltext.length(); i++) {
			delim = "";
			if (set.contains(fulltext.charAt(i))) {
				sb.append(fulltext.charAt(i));
			}
			else {
				if (sb.length() != 0) {
					word = sb.toString();
					sb.delete(0, sb.length());
					binaryCodes.append(codes.get(word));
				}
				delim += fulltext.charAt(i);
				binaryCodes.append(codes.get(delim));
			}
		}
	}
	
	public void binaryCodes2(String fulltext) {
		String word = "";
		String delim = "";
		StringBuilder sb = new StringBuilder(256);
		Set<Character> set = Stream.of('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','-','\'').collect(Collectors.toSet());
		for (int i = 0; i < fulltext.length(); i++) {
			delim = "";
			if (set.contains(fulltext.charAt(i))) {
				sb.append(fulltext.charAt(i));
			}
			else {
				if (sb.length() != 0) {
					word = sb.toString();
					sb.delete(0, sb.length());
					binaryCodes2.append(codes2.gett(word));
				}
				delim += fulltext.charAt(i);
				binaryCodes2.append(codes2.gett(delim));
			}
		}
	}
	   
	   public byte[] createBits(StringBuilder binaryCodes) {
		   bits = new byte[binaryCodes.length()/8];
		   for (int i = 0; i < bits.length; i++) {
			   bits[i] = (byte)Integer.parseInt(binaryCodes.substring(i*8, (i*8)+8), 2);
	   	   }
		   return bits;
	   }
	   
	   public byte[] createBits2(StringBuilder binaryCodes) {
		   bits2 = new byte[binaryCodes2.length()/8];
		   for (int i = 0; i < bits2.length; i++) {
			   bits2[i] = (byte)Integer.parseInt(binaryCodes2.substring(i*8, (i*8)+8), 2);
	   	   }
		   return bits2;
	   }
}

