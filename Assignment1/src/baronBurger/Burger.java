package baronBurger;

public class Burger {
	private MyStack<String> myStack;
	private Recipe baronBurger;
	private int myPattyCount;
	private String myPattyType;
	private boolean myWorks;
	
	public Burger(boolean theWorks) {
		myWorks = theWorks;
		myStack = new MyStack<String>();
		baronBurger = new Recipe();
		myPattyCount = 1;
		myPattyType = "Beef";
		if (!myWorks) {
			myStack.push("Bun");
			myStack.push(myPattyType);
			myStack.push("Bun");
		}
		else {
			myStack = baronBurger.getRecipe();
		}
	}
	
	/* public void changeRecipe() {
		MyStack<String> prevItems = new MyStack<String>();
		int size = baronBurger.getRecipe().size();
		for (int i = 0; i < size; i++) {
			String top = baronBurger.getRecipe().peek();
			if (top == "Pepperjack") {
				break;
			}
			else
				prevItems.push(top);
				baronBurger.getRecipe().pop();
		}
		for (int i = 0; i < 3; i++) {
			String top = baronBurger.getRecipe().peek();
			baronBurger.getRecipe().pop();
			if (top == "Beef" ) {
				baronBurger.getRecipe().push(myPattyType);
			}
		}
		baronBurger.getRecipe().push("Cheddar");
		baronBurger.getRecipe().push("Mozzarella");
		baronBurger.getRecipe().push("Pepperjack");
		if (myPattyCount == 2) {
			baronBurger.getRecipe().push(myPattyType);
		}
		if (myPattyCount == 3) {
			baronBurger.getRecipe().push(myPattyType);
			baronBurger.getRecipe().push(myPattyType);
		}
		size = prevItems.size();
		for (int i = 0; i < size; i++) {
			baronBurger.getRecipe().push(prevItems.pop());
		}
	} */
	
	public void addPatty() {
		Recipe baronBurger = new Recipe();
		MyStack<String> prevItems = new MyStack<String>();
		String top;
		int size = baronBurger.getRecipe().size();
		for (int i = 0; i < size; i++) {
			top = baronBurger.getRecipe().peek();
			if (myStack.peek().equals("Cheddar") || myStack.peek().equals("Mozzarella") || myStack.peek().equals("Pepperjack") || myStack.peek().equals(myPattyType)) {
				break;
			}
			if (top.equals(myStack.peek())) {
				prevItems.push(top);
				myStack.pop();
			}
		baronBurger.getRecipe().pop();
		}
		if (myPattyCount < 3) {
			myStack.push(myPattyType);
			myPattyCount++;
		}
		size = prevItems.size();
		for (int i = 0; i < size; i++) {
			myStack.push(prevItems.peek());
			prevItems.pop();
		}
	}
	
	public void changePatties(String pattyType) {
		MyStack<String> prevItems = new MyStack<String>();
		int size = myStack.size();
		if (pattyType.equals("Beef") || pattyType.equals("Chicken") || pattyType.equals("Veggie")) {
  			for (int i = 0; i < size; i++) { 
				if (myStack.peek().equals(myPattyType)) {
					myStack.pop();
					prevItems.push(pattyType);
				}
				else {
					prevItems.push(myStack.pop());
				}
			}
			myPattyType = pattyType;
			size = prevItems.size();
			for (int i = 0; i < size; i++) {
				myStack.push(prevItems.pop());
			}
		}

	}
	
	public String getPattyType() {
		return myPattyType;
	}
	
	public void addCategory(String type) {
		Recipe baronBurger = new Recipe();
		int cheeseSize = baronBurger.getCheeses().size();
		int sauceSize = baronBurger.getSauces().size();
		int veggieSize = baronBurger.getVeggies().size();
		if (type.equals("Cheese")) {
			for (int i = 0; i < cheeseSize; i++) {
				addIngredient(baronBurger.getCheeses().pop());
			}
		}
		if (type.equals("Sauce")) {
			for (int i = 0; i < sauceSize; i++) {
				addIngredient(baronBurger.getSauces().pop());
			}
		}
		if (type.equals("Veggies")) {
			for (int i = 0; i < veggieSize; i++) {
				addIngredient(baronBurger.getVeggies().pop());
			}
		}			

	}
	
	public void addIngredient(String type) {
		MyStack<String> prevItems = new MyStack<String>();
		Recipe baronBurger = new Recipe();
		int size = baronBurger.getRecipe().size();
		for (int i = 0; i < size; i++) {
			if (baronBurger.getRecipe().peek().equals(myStack.peek())) {
				prevItems.push(baronBurger.getRecipe().peek());
				myStack.pop();
			}
			if (baronBurger.getRecipe().peek().equals(type)) {
				prevItems.push(baronBurger.getRecipe().peek());
			}
			baronBurger.getRecipe().pop();
		}
		size = prevItems.size();
		for (int i = 0; i < size; i++) {
			myStack.push(prevItems.peek());
			prevItems.pop();
		}
	}
	
	public void removeCategory(String type) {
		Recipe baronBurger = new Recipe();
		int cheeseSize = baronBurger.getCheeses().size();
		int sauceSize = baronBurger.getSauces().size();
		int veggieSize = baronBurger.getVeggies().size();
		if (type.equals("Cheese")) {
			for (int i = 0; i < cheeseSize; i++) {
				removeIngredient(baronBurger.getCheeses().pop());
			}
		}
		if (type.equals("Sauce")) {
			for (int i = 0; i < sauceSize; i++) {
				removeIngredient(baronBurger.getSauces().pop());
			}
		}
		if (type.equals("Veggies")) {
			for (int i = 0; i < veggieSize; i++) {
				removeIngredient(baronBurger.getVeggies().pop());
			}
		}

	}
	
	public void removeIngredient(String type) {
		MyStack<String> prevItems = new MyStack<String>(); 
		while (!myStack.peek().equals(type)) {	
			prevItems.push(myStack.peek());
			myStack.pop();
		}
		myStack.pop();
		int size = prevItems.size();
		for (int i = 0; i < size; i++) {
			myStack.push(prevItems.peek());
			prevItems.pop();
		}
	}
	
	@Override
	public String toString() {
		return myStack.toString();
	}
}

