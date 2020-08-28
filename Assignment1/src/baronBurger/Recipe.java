package baronBurger;

import java.util.Arrays;
import java.util.List;

public class Recipe {
	MyStack<String> recipe = new MyStack<String>();
	MyStack<String> cheeses = new MyStack<String>();
	MyStack<String> sauces = new MyStack<String>();
	MyStack<String> veggies = new MyStack<String>();
	List<String> list = Arrays.asList("Bun", "Ketchup", "Mustard", "Mushrooms", "Beef",
			"Cheddar", "Mozzarella", "Pepperjack", "Onions", "Tomato", "Lettuce",
			"Baron-Sauce", "Mayonnaise", "Bun", "Pickle");
	
	public Recipe () {
		for (String ingredients : list) {
			recipe.push(ingredients);
			if (ingredients == "Cheddar" || ingredients == "Mozzarella" || ingredients == "Pepperjack") {
				cheeses.push(ingredients);
			}
			if (ingredients == "Ketchup" || ingredients == "Mustard" || ingredients == "Baron-Sauce" || ingredients == "Mayonnaise") {
				sauces.push(ingredients);
			}
			if (ingredients == "Mushrooms" || ingredients == "Onions" || ingredients == "Tomato" || ingredients == "Lettuce" || ingredients == "Pickle" ) {
				veggies.push(ingredients);
			}
		}
	}
	
	public MyStack<String> getRecipe() {
		return recipe;
	}
	
	public MyStack<String> getCheeses() {
		return cheeses;
	}
	
	public MyStack<String> getSauces() {
		return sauces;
	}
	
	public MyStack<String> getVeggies() {
		return veggies;
	}
}

