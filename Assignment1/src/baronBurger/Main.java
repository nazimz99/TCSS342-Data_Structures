package baronBurger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static int orderNum = 0;
	private static Scanner scanner;
	private static BufferedReader br;
	private static Burger burger;
	
	public static void main(String[] args) throws Exception {
		testMyStack();
		testBurger();
		File file = new File("./customer2.txt");
		br = new BufferedReader(new FileReader(file));
		scanner = new Scanner(file);
		while (scanner.hasNextLine() && br.readLine() != null) {
			String str = scanner.nextLine();
			parseLine(str);
			orderNum++;
		} 
	}
	
	public static void parseLine(String line) {
		boolean addPatty = false;
		String delim = " ";
		String[] output = line.split(delim);
		List<String> input = new ArrayList<String>();
		List<String> end = new ArrayList<String>();
		List<String> categories = Arrays.asList("Cheese", "Sauce", "Veggies");
		List<String> ingredients = Arrays.asList("Ketchup", "Mustard", "Mushrooms", 
				"Cheddar", "Mozzarella", "Pepperjack", "Onions", "Tomato", "Lettuce",
				"Baron-Sauce", "Mayonnaise", "Pickle");
		System.out.println("Processing Order " + orderNum + ": " + line);
		
		if (line.contains("Baron")) {
			burger = new Burger(true);
		}
		else 
			burger = new Burger(false);
		
		for (int i = 0; i < output.length; i++) {
			if (!output[i].equals("but")) {
				input.add(output[i]);
			}
			else 
				break;
		}
		for (int i = input.size(); i < output.length; i++) {
			end.add(output[i]);
		}
		
		for (int i = 0; i < input.size(); i++) {
			if (input.contains("no")) {
				if (categories.contains(input.get(i))) {
					burger.removeCategory(input.get(i));
				}
				if (ingredients.contains(input.get(i))) {
					burger.removeIngredient(input.get(i));
				}
			}
			else {
				if (categories.contains(input.get(i))) {
					burger.addCategory(input.get(i));
				}
				if (ingredients.contains(input.get(i))) {
					burger.addIngredient(input.get(i));
				}
			}
		}
		
		for (int i = 0; i < end.size(); i++) {
			if (end.contains("no")) {
				if (categories.contains(end.get(i))) {
					burger.removeCategory(end.get(i));
				}
				if (ingredients.contains(end.get(i))) {
					burger.removeIngredient(end.get(i));
				}
			}
			else {
				if (categories.contains(end.get(i))) {
					burger.addCategory(end.get(i));
				}
				if (ingredients.contains(end.get(i))) {
					burger.addIngredient(end.get(i));
				}
			}
		}
			
		addPatty = true;
		if (addPatty) {
			if (line.contains("Double")) {
				burger.addPatty();
			}
			if (line.contains("Triple")) {
				burger.addPatty();
				burger.addPatty();
			}
			for (int i = 0; i < output.length; i++) {
				if (output[i].equals("Beef")) {
					burger.changePatties("Beef");
				}
				if (output[i].equals("Chicken")) {
					burger.changePatties("Chicken");
				}
				if (output[i].equals("Veggie")) {
					burger.changePatties("Veggie");
				}			
			}
		}
		System.out.println(burger);
	}

	
	public static void testMyStack() {
		MyStack<String> testStack = new MyStack<String>();
		System.out.println(testStack.isEmpty() + " " + testStack.peek() + " " + testStack.pop());
		testStack.push("Apple");
		System.out.println(testStack.isEmpty() + " " + testStack.peek() + " " + testStack.pop());
		testStack.push("Apple");
		System.out.println("Size of stack " + testStack.size() + " " + testStack.peek());
		testStack.push("Bike");
		System.out.println("Size of stack " + testStack.size() + " " + testStack.peek());
		testStack.push("Computer");
		System.out.println("Size of stack " + testStack.size() + " " + testStack.peek());
		testStack.push("Dog");
		System.out.println("Size of stack " + testStack.size() + " " + testStack.peek());
		System.out.println(testStack);
		int size = testStack.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			System.out.println(testStack.peek());
			testStack.pop();
		}

	}
	
	public static void testBurger() {
		Burger burger = new Burger(false);
		System.out.println(burger);
		burger.addCategory("Cheese");
		System.out.println(burger);
		burger.addIngredient("Tomato");
		burger.addIngredient("Mushrooms");
		burger.addIngredient("Lettuce");
		burger.addIngredient("Mustard");
		burger.addIngredient("Mayonnaise");
		burger.addIngredient("Baron-Sauce");
		System.out.println(burger);
		burger.removeIngredient("Lettuce");
		burger.removeIngredient("Mushrooms");
		burger.addIngredient("Ketchup");
		System.out.println(burger);
		burger.removeCategory("Sauce");
		System.out.println(burger);
		burger.removeIngredient("Tomato");
		burger.addCategory("Veggies");
		burger.addPatty();
		burger.changePatties("Veggie");
		System.out.println(burger);
		burger.addPatty();
		burger.changePatties("Chicken");
		System.out.println(burger);
		burger = new Burger(true);
		System.out.println("\n" + burger);
		burger.addPatty();
		burger.addPatty();
		burger.addPatty();
		System.out.println(burger);
		burger = new Burger(true);
		burger.removeCategory("Sauce");
		burger.removeIngredient("Mozzarella");
		burger.addCategory("Mozzarella");
		burger.removeCategory("Mozzarella");
		System.out.println(burger);
	}
}
