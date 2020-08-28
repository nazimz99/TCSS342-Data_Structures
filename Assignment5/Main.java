public class Main {
	static Maze maze = new Maze(5, 5, true);
	
	public static void main(String[] args) {
		System.out.println(maze);
		System.out.println("----------------------------------------");
		maze.depthFirstSearch();
	}
}