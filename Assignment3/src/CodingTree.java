import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class CodingTree {
	  
	class Node implements Comparable<Node> {
	      int frequency;
	      String code;
	      String binaryCode;
	      char ch;
	      Node left;
	      Node right;
	      
	      public Node(int frequency, Node left, Node right) {
	    	  this.ch = '\u0000';
	    	  this.frequency = frequency;
	    	  this.left = left;
	    	  this.right = right;
	    	  binaryCode = "";
	      }
	      
	      public Node (char ch, int frequency) {
	         this.ch = ch;
	         this.frequency = frequency;
	         binaryCode = "";
	         right = null;
	         left = null;
	      }
	   
	      boolean isLeaf() {
	         return this.right  == null && this.left == null;
	      }
	   
	      public String toString() {
	    	  String result = "";
	    	  result += ch;
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
	   public Map<Character, Integer> map;
	   public Map<Character, String> codes;
	   private String message;
	   public byte[] bits;
	   public MyPriorityQueue<Node> tree;
	   public StringBuilder binaryCodes;
	   public StringBuilder convertBits;
	  
	   public CodingTree(String message) {
	      root = null;
	      map = new HashMap<Character, Integer>();
	      codes = new HashMap<Character, String>();
	      this.message = message;
	      tree = new MyPriorityQueue<Node>();
	      //tree2 = new MyPriorityQueue<Node>();
	      binaryCodes = new StringBuilder(256);
	      //decoded = "";
	      frequencyTree();
	      constructTree();
	      merge();
	      traversal(tree.peek(), tree.peek().binaryCode);
	      binaryCodes(message);
	      createBits(binaryCodes); 
	   }
	   
	   public Map<Character, Integer> frequencyTree() {
		   for (int i = 0; i < message.length(); i++) {
			   if (!map.containsKey(message.charAt(i))) {
				   map.put(message.charAt(i), 1);
			   }
			   else {
				   map.put(message.charAt(i), map.get(message.charAt(i)) + 1);
			   }
		   }
		   return map;
	   }
	   
	   public void constructTree() {
		   Iterator<Character> itr = map.keySet().iterator();
		   while (itr.hasNext()) {
			   Character ch = itr.next();
			   tree.add(new Node(ch, map.get(ch)));
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
	   
	   /** The root of the PriorityQueue is the parameter for the traversal.
	    * This will traverse left/right through the tree until a leaf is found.
	    * The leaf's character and binaryCode (incremented through traversing left/right)
	    * is then added to a HashMap<Character, String> with its character and corresponding binarycode.
	   */
	   public void traversal(Node n, String binaryCode) {
		   if (n.isLeaf() && n.ch != '\u0000') {
			   codes.put(n.ch, binaryCode);
			   return;
		   }
		   traversal(n.left, binaryCode + "0");
		   traversal(n.right, binaryCode + "1");
	   }	
	   
	   public StringBuilder binaryCodes(String message) {
		   for (int i = 0; i < message.length(); i++) {
			   binaryCodes.append(codes.get(message.charAt(i)));
		   }
		   return binaryCodes;
	   }
	   
	   public byte[] createBits(StringBuilder binaryCodes) {
		   bits = new byte[binaryCodes.length()/8];
		   for (int i = 0; i < bits.length; i++) {
			   bits[i] = (byte)Integer.parseInt(binaryCodes.substring(i*8, (i*8)+8), 2);
			  // allBits = String.format("%8s", Integer.toBinaryString((bits[i] + 128)));
	   	   }
		   return bits;
	   }
	   
	   public void decode(String bits, Map<Character, String> codes) throws IOException {
		   byte[] reverse = Files.readAllBytes(Paths.get(bits));
		   StringBuilder sb = new StringBuilder();
		   convertBits = new StringBuilder();
		   String output = "";
		   
		   for (int i = 0; i < reverse.length-1; i++) {
			   if (reverse.length % 8 != 0 && i == reverse.length - 2) {
				   sb.append(String.format("%" + reverse[i+1] + "s", Integer.toBinaryString(reverse[i])).replace("", "0"));
				   break;
			   }
			   if (reverse[i] < 0) {
				   sb.append(Integer.toBinaryString(reverse[i]).substring(24, 32));
			   }
			   else {
				   sb.append(String.format("% 8s", Integer.toBinaryString(reverse[i])).replace(' ', '0'));
			   }
			   
			   for (int j = 0; j < reverse.length; j++) {
				   output += Character.toString(sb.charAt(i));
				   if (codes.containsValue(output)) {
					   for (Map.Entry<Character, String> entry : codes.entrySet()) {
						   if (entry.getValue().equals(output)) {
							   convertBits.append(entry.getKey());
							   output = "";
						   }
					   }
				   }
			   }
		   }
		   
	   }
	    
	   /*public void convertBytes(byte[] bits) {
		   for (int i = 0; i < bits.length; i++) {
			   allBits += String.format("%8s", Integer.toBinaryString((bits[i] + 128)));
		   }
	   }
	   
	   public String decode(String bits, Map<Character, String> codes) {
		   String decoded = "";
		   tempBits = new StringBuilder(256);
		   List<Character> tempChars = new ArrayList<Character>();
		   Iterator<Character> itr = codes.keySet().iterator();
		 }
		   }
		   return decoded;
	   } */
}
