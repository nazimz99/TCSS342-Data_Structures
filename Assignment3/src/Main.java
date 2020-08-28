import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	public static Scanner scanner;
	public static StringBuilder sb;
	public static File file;
	public static CodingTree tree;
	
	public static void main(String[] args) throws Exception {
		//testCodingTree();
		
		long start = System.nanoTime();
		file = new File("./WarAndPeace.txt");
		scanner = new Scanner(file);
		String message = new String(Files.readAllBytes(Paths.get("WarAndPeace.txt")), StandardCharsets.UTF_8);
		tree = new CodingTree(message);
		FileOutputStream fs = new FileOutputStream("EncodeWarAndPeace.txt");
		fs.write(tree.bits);
		fs.close();
		//tree.decode(tree.binaryCodes.toString(), tree.codes);
		//System.out.println(tree.convertBits.toString());
		//tree.decode(tree.allBits, tree.codes);
		//tree.convertBytes(tree.bits);
		
		System.out.println("Original file size: " + Files.size(Paths.get("WarAndPeace.txt"))/1024);
		System.out.println("Output file size: " + Files.size(Paths.get("EncodeWarAndPeace.txt"))/1024);
		System.out.println("Compression ratio: " + ((double)(Files.size(Paths.get("EncodeWarAndPeace.txt"))) / (double)Files.size(Paths.get("WarAndPeace.txt"))) * 100 + "%");
		long end = System.nanoTime();
		long nanoDuration = end - start;
		long millisDuration =  TimeUnit.NANOSECONDS.toMillis(nanoDuration);
		System.out.println("Encode run time: " + millisDuration + " milliseconds" + "\n");
	}
	
	public static void testCodingTree() {
		String message = "Super Smash Bros Ultimate";
		CodingTree tree = new CodingTree(message);
		StringBuilder sb = new StringBuilder(256);
		sb.append(message);
		tree.frequencyTree();
		tree.constructTree();
		System.out.println(tree.tree);
		tree.merge();
		System.out.println(tree.tree.peek());
		tree.traversal(tree.tree.peek(), tree.tree.peek().binaryCode);
		Iterator<Character> itr = tree.codes.keySet().iterator();
		while (itr.hasNext()) {
			Character ch = itr.next();
			System.out.println(ch + ", " + tree.codes.get(ch));
		}
	    tree.binaryCodes(message);
		System.out.println("\n");
		tree.createBits(tree.binaryCodes);
		for (int i = 0; i < tree.bits.length; i++) {
			System.out.println(tree.bits[i]);
		} 
	} 
}

