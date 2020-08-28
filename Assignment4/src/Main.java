import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	public static Scanner scanner;
	public static StringBuilder sb;
	public static File file;
	public static CodingTree tree;
	public static CodingTree tree2;
	
	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();
		file = new File("./WarAndPeace.txt");
		scanner = new Scanner(file);
		String fulltext = new String(Files.readAllBytes(Paths.get("WarAndPeace.txt")));
		tree = new CodingTree(fulltext);
		tree.traversal(tree.tree.peek(), "");
		tree.binaryCodes(fulltext);
		tree.createBits(tree.binaryCodes);
		tree.map.stats();
		FileOutputStream fs = new FileOutputStream("EncodeWarAndPeace.txt");
		fs.write(tree.bits);
		fs.close(); 
		
		System.out.println(tree.map.size());
		System.out.println("Original file size: " + Files.size(Paths.get("WarAndPeace.txt"))/1024);
		System.out.println("Output file size: " + Files.size(Paths.get("EncodeWarAndPeace.txt"))/1024);
		System.out.println("Compression ratio: " + ((double)(Files.size(Paths.get("EncodeWarAndPeace.txt"))) / (double)Files.size(Paths.get("WarAndPeace.txt"))) * 100 + "%");
		long end = System.nanoTime();
		long nanoDuration = end - start;
		long millisDuration =  TimeUnit.NANOSECONDS.toMillis(nanoDuration);
		System.out.println("Encode run time: " + millisDuration + " milliseconds" + "\n");
		
		/** Run time and compression for alternate collision handling strategy */
	    start = System.nanoTime();
		tree2 = new CodingTree(fulltext);
		tree2.frequencyTree2();
		tree2.constructTree2();
		tree2.merge2();
		tree2.traversal2(tree2.tree2.peek(), "");
		tree2.binaryCodes2(fulltext);
		tree2.createBits2(tree2.binaryCodes2);
		tree2.map2.stats2();
		FileOutputStream fs2 = new FileOutputStream("EncodeWarAndPeace2.txt");
		fs2.write(tree2.bits2);
		fs2.close(); 

		System.out.println("Original file size: " + Files.size(Paths.get("WarAndPeace.txt"))/1024);
		System.out.println("Output file size: " + Files.size(Paths.get("EncodeWarAndPeace2.txt"))/1024);
		System.out.println("Compression ratio: " + ((double)(Files.size(Paths.get("EncodeWarAndPeace2.txt"))) / (double)Files.size(Paths.get("WarAndPeace.txt"))) * 100 + "%");
		end = System.nanoTime();
		nanoDuration = end - start;
		millisDuration =  TimeUnit.NANOSECONDS.toMillis(nanoDuration);
		System.out.println("Encode run time: " + millisDuration + " milliseconds"); 
		
		//testHashTable();
	}
	
	public static void testHashTable() {
		MyHashTable<String, Integer> table = new MyHashTable<String, Integer>(10);
		List<String> list = new ArrayList<String>(Arrays.asList("word", "word", "apple", "dog", "cat", "dog", "dog"));
		for (int i = 0; i < list.size(); i++) {
			if (!table.containsKey(list.get(i))) {
				table.put(list.get(i), 1);
			}
			else {
				table.put(list.get(i), table.get(list.get(i)) + 1);
			}
		}
		System.out.println(table);
		System.out.println(table.get("apple"));
		System.out.println(table.get("cat"));
		System.out.println(table.get("word"));
		System.out.println(table.get("dog"));
		System.out.println(table.containsKey("alpha"));
		System.out.println(table.containsKey("apple"));
		System.out.println(table.size());
		System.out.println(table.keySet());
		table.stats();
	}
}
