package compression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static Scanner scanner;
	public static BufferedReader br;
	public static File file;
	public static StringBuilder sb;
	
	public static StringBuilder getStringBuilder() {
		return sb;
	}
	
	public static void main(String[] args) throws Exception {
		file = new File("/.WarAndPeace.txt");
		scanner = new Scanner(file);
		br = new BufferedReader(new FileReader(file));
		String element = br.readLine();
		while (scanner.hasNext() && br.readLine() != null) {
			String str = scanner.nextLine();
			sb.append(element);
			parseLine(str);
		}
		
	}
	
	public static void parseLine(String line) {
		String[] elements = line.split(" "); 
	}
}
