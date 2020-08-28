import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
	int width;
	int depth;
	int realWidth;
	int realDepth;
	boolean debug;
	Node[][] maze;
	Random randalf = new Random();
	int startX = 0;
	int startY = 0;
	int endX = 0;
	int endY = 0;
	public Maze(int width, int depth, boolean debug) {
		this.width = width;
		this.depth = depth;
		this.debug = debug;
		this.realWidth = 2*this.width+1;
		this.realDepth = 2*this.depth+1;
		maze = new Node[this.realWidth][this.realDepth];
		Node node = null;
		this.endX = this.realWidth-1;
		this.endY = this.realDepth-1;
		for (int i = 0; i < this.realWidth; i++) {
			for (int j = 0; j < this.realDepth; j++) {
				node = new Node('X', i, j);
				if (i == startX && j == startY) node = new Node('S', i ,j);
				if (i == endX && j == endY) node = new Node('E', i, j);
				maze[i][j] = node;
			}
		}
		generateMaze();
	}
	
	public List<Node> getEdges(int i, int j, Node[][] maze) {
		List<Node> edges = new ArrayList<Node>();
		if (i-1 == 0) edges.add(maze[i-1][j]);
		if (i+1 == this.realWidth-1) edges.add(maze[i+1][j]);
		if (j-1 == 0) edges.add(maze[i][j-1]);
		if (j+1 == this.realDepth+1) edges.add(maze[i][j+1]);
		return edges;
	}
	
	public List<Node> getWalls(int i, int j, Node[][] maze) {
		List<Node> walls = new ArrayList<Node>();
		if (i > 1 && !maze[i-2][j].created) walls.add(maze[i-2][j]);
		//else walls.add(null);
		if (j > 1 && !maze[i][j-2].created) walls.add(maze[i][j-2]);
		//else walls.add(null);
		if (i < this.realWidth-2 && !maze[i+2][j].created) walls.add(maze[i+2][j]);
		//else walls.add(null);
		if (j < this.realDepth-2 && !maze[i][j+2].created) walls.add(maze[i][j+2]);
		//else walls.add(null);
		/*if (i-1 == 0 && !maze[i-1][j].created) walls.add(maze[i-1][j]);
		if (i+1 == this.realWidth-1 && !maze[i+1][j].created) walls.add(maze[i+1][j]);
		if (j-1 == 0 && !maze[i][j-1].created) walls.add(maze[i][j-1]);
		if (j+1 == this.realDepth-1 && !maze[i][j+1].created) walls.add(maze[i][j+1]);*/
		return walls;
	}
	
	public void generateMaze() {
		generateMaze(endX-1, endY-1, maze, new ArrayList<Node>());
	}
	
	/*public void generateMaze(int i, int j, Node[][] maze, List<Node> queue) {
		List<Node> wallList = getWalls(i, j, maze);
		Map<Node, List<Node>> map = new HashMap<Node, List<Node>>();
		if (wallList.size() > 0) map.put(maze[i][j], wallList);
		for (Node node : map.keySet()) {
			queue.add(node);
		}
		double index = Math.floor(queue.size() * randalf.nextDouble());
		Node node = queue.get((int)index);
		
		while (!map.isEmpty()) {
			if (node.ch != 'S' && node.ch != 'E') node.ch = ' ';
			node.created = true;
			List<Node> walls = map.get(node);
			double location = Math.floor(walls.size() * randalf.nextDouble());
			Node next = walls.get((int)location);
	 
			if (next != null && !next.created) {
				maze[next.i][next.j].created = true;
				if (maze[next.i][next.j].ch == 'X') maze[next.i][next.j].ch = ' ';
				connectEdge(location, node, next, maze, walls);
				List<Node> nextWalls = getWalls(next.i, next.j, maze);
				if (nextWalls.size() > 0 && !queue.contains(next)) {
					queue.add(next);
					map.put(next, nextWalls);
				}
			}
			/*else if (next != null && next.created){
				maze[next.i][next.j].created = true;
				maze[next.i][next.j].ch = 'X';
			}
			walls.remove(next);
			if (walls.size() == 0) {
				map.remove(node);
				queue.remove(node);
				if (queue.size() == 0) break;
			}
			index = Math.floor(queue.size() * randalf.nextDouble());
			node = queue.get((int)index);
		}
	}*/
	
	/* 
	 * Connects the next area of the maze to a prexisting path.
	 */
	public void connectEdge(double location, Node node, Node next, Node[][] maze, List<Node> walls) {
		if (walls.size() > 0 && next != null) {
			if (location == 0) {
				if (next.i == node.i+2 && maze[node.i+1][node.j].ch == 'X') maze[node.i+1][node.j].ch = ' ';
				else if (next.i == node.i-2 && maze[node.i-1][node.j].ch == 'X') maze[node.i-1][node.j].ch = ' ';
				else if (next.j == node.j+2 && maze[node.i][node.j+1].ch == 'X') maze[node.i][node.j+1].ch = ' ';
				else if (next.j == node.j-2 && maze[node.i][node.j-1].ch == 'X') maze[node.i][node.j-1].ch = ' ';	
			}
			else if (location == 1) {
				if (next.i == node.i+2 && maze[node.i+1][node.j].ch == 'X') maze[node.i+1][node.j].ch = ' ';
				else if (next.i == node.i-2 && maze[node.i-1][node.j].ch == 'X') maze[node.i-1][node.j].ch = ' ';
				else if (next.j == node.j+2 && maze[node.i][node.j+1].ch == 'X') maze[node.i][node.j+1].ch = ' ';
				else if (next.j == node.j-2 && maze[node.i][node.j-1].ch == 'X') maze[node.i][node.j-1].ch = ' ';	
			}
			else if (location == 2) {
				if (next.i == node.i+2 && maze[node.i+1][node.j].ch == 'X') maze[node.i+1][node.j].ch = ' ';
				else if (next.i == node.i-2 && maze[node.i-1][node.j].ch == 'X') maze[node.i-1][node.j].ch = ' ';
				else if (next.j == node.j+2 && maze[node.i][node.j+1].ch == 'X') maze[node.i][node.j+1].ch = ' ';
				else if (next.j == node.j-2 && maze[node.i][node.j-1].ch == 'X') maze[node.i][node.j-1].ch = ' ';	
			}
			else if (location == 3) {
				if (next.i == node.i+2 && maze[node.i+1][node.j].ch == 'X') maze[node.i+1][node.j].ch = ' ';
				else if (next.i == node.i-2 && maze[node.i-1][node.j].ch == 'X') maze[node.i-1][node.j].ch = ' ';
				else if (next.j == node.j+2 && maze[node.i][node.j+1].ch == 'X') maze[node.i][node.j+1].ch = ' ';
				else if (next.j == node.j-2 && maze[node.i][node.j-1].ch == 'X') maze[node.i][node.j-1].ch = ' ';	
			}
		}
	}
	
	public void generateMaze(int i, int j, Node[][] maze, List<Node> queue) {
		queue.add(maze[i][j]);
		while (!queue.isEmpty()) {
			double index = Math.floor(queue.size() * randalf.nextDouble());
			Node node = queue.get((int)index);
			//node.created = true;
			if (node.ch == 'X') node.ch = ' ';
			maze[startX][startY+1].ch = ' ';
			maze[startX+1][startY].ch = ' ';
			maze[endX-1][endY].ch = ' ';
			maze[endX][endY-1].ch = ' ';
			maze[node.i][node.j] = node;
			
			// Selecting a random wall of the node to connect.
			List<Node> walls = getWalls(node.i, node.j, maze);
			List<Node> edges = getEdges(node.i, node.j, maze);
			//Add unvisited walls to the queue.
			for (int k = 0; k < walls.size(); k++) {
				//if (walls.get(k).i % 2 == 1 && walls.get(k).j % 2 == 1) 
					queue.add(walls.get(k));
			}
			
			double location = Math.floor(walls.size() * randalf.nextDouble());
			index = Math.floor(edges.size() * randalf.nextDouble());
			Node next = null;
			Node edge = null;
			//int edges = numOfEdges(node.i, node.j, maze);
			if (walls.size() > 0) next = walls.get((int)location);
			if (edges.size() > 0) {
				edge = edges.get((int)index);
				if (maze[edge.i][edge.j].ch == 'X') maze[edge.i][edge.j].ch = ' ';
			}
			if (next != null && !next.created) {
				if (next.ch == 'X') next.ch = ' ';
				next.created = true;
				connectEdge(location, node, next, maze, walls);
				if (!queue.contains(next)) queue.add(next);
			}
			//queue.remove(node);
			if (walls.size() == 0) queue.remove(node);
		}
	}
	
	public boolean canTraverse(int i, int j, Node[][] maze) {
		boolean left = true;
		boolean right = true;
		boolean north = true;
		boolean south = true;
		
		if (i == this.realWidth-1) right = false;
		else if (maze[i+1][j].visited || maze[i+1][j].ch == 'X') right = false;
		if (j == this.realDepth-1) south = false;
		else if (maze[i][j+1].visited || maze[i][j+1].ch == 'X') south = false;
		if (i == 0) left = false;
		else if (maze[i-1][j].visited || maze[i-1][j].ch == 'X') left = false;
		if (j == 0) north = false;
		else if (maze[i][j-1].visited || maze[i][j-1].ch == 'X') north = false;
		
		if (!left && !right && !north && !south) return false;
		else return true;
	}
	
	/** Depth first traversal 
	 * Traverses through each valid direction at a time.
	 * Backtracks if invalid path is reached to the previous
	 * spot to search through another valid path.
	 **/
	
	public void depthFirstSearch() {
		depthFirstSearch(this.startX, this.startY, this.maze);
	}
	
	public void depthFirstSearch(int i, int j, Node[][] maze) {
		maze[i][j].visited = true;
		if (maze[i][j].ch == ' ') maze[i][j].ch = 'V';
		if (this.debug) {
			System.out.println(this);
			System.out.println("----------------------------------------");
		}
		if (i == this.endX && j == this.endY) return;
		else if (canTraverse(i, j, maze)) {
			boolean traversed = false;
			if (i < this.realWidth-1 && maze[i+1][j].ch != 'X' && !maze[i+1][j].visited && !traversed) {
				maze[i+1][j].prev = maze[i][j];
			    //if (!canTraverse(i+1, j, maze)) maze[i+1][j].backtrack = true;
				//depthFirstSearch(i+1, j, maze, initial+1, bottom);
			    depthFirstSearch(i+1, j, maze);
				traversed = true;
			}
			else if (j < this.realDepth-1 && maze[i][j+1].ch != 'X' && !maze[i][j+1].visited && !traversed) {
				maze[i][j+1].prev = maze[i][j];
				//if (!canTraverse(i, j+1, maze)) maze[i][j+1].backtrack = true;
				//depthFirstSearch(i, j+1, maze, initial+1, bottom);
				depthFirstSearch(i, j+1, maze);
				traversed = true;
			}
			else if (i > 0 && maze[i-1][j].ch != 'X' && !maze[i-1][j].visited && !traversed) {
				maze[i-1][j].prev = maze[i][j];
				//if (!canTraverse(i-1, j, maze)) maze[i-1][j].backtrack = true;
				//depthFirstSearch(i-1, j, maze, initial+1, bottom);
				depthFirstSearch(i-1, j, maze);
				traversed = true;
			}
			else if (j > 0 && maze[i][j-1].ch != 'X' && !maze[i][j-1].visited && !traversed) {
				maze[i][j-1].prev = maze[i][j];
				//if (!canTraverse(i, j-1, maze)) maze[i][j-1].backtrack = true;
				//depthFirstSearch(i, j-1, maze, initial+1, bottom);
				depthFirstSearch(i, j-1, maze);
				traversed = true;
			}
		}
		else {
			if (i > 0 && maze[i][j].prev == maze[i-1][j]) {
				maze[i][j].ch = ' ';
				maze[i][j].prev = null;
				//depthFirstSearch(i-1, j, maze, initial-1, bottom);
				depthFirstSearch(i-1, j, maze);
			}
			else if (j > 0 && maze[i][j].prev == maze[i][j-1]) {
				maze[i][j].ch = ' ';
				maze[i][j].prev = null;
				//depthFirstSearch(i, j-1, maze, initial-1, bottom);
				depthFirstSearch(i, j-1, maze);
			}
			else if (i < this.realWidth-1 && maze[i][j].prev == maze[i+1][j]) {
				maze[i][j].ch = ' ';
				maze[i][j].prev = null;
				//depthFirstSearch(i+1, j, maze, initial-1, bottom);
				depthFirstSearch(i+1, j, maze);
			}
			else if (j < this.realDepth-1 && maze[i][j].prev == maze[i][j+1]) {
				maze[i][j].ch = ' ';
				maze[i][j].prev = null;
				//depthFirstSearch(i, j+1, maze, initial-1, bottom);
				depthFirstSearch(i, j+1, maze);
			}
		}
	}
	
	/*public void iterativeDeepening() {
		int depth = 1;
		while (maze[endX][endY].ch == 'E') {
			depthFirstSearch(depth);
			depth++;
			System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		}
	}*/
	
	/** Breadth first traversal 
	 * Sets references to previous nodes visited in order to find path to the end.
	 * Any incorrect paths are erased and user backtracks to previous area to 
	 * search for next valid path.
	 **/
	
	public Node breadthFirstSearch() {
		return breadthFirstSearch(this.endX, this.endY, this.maze, new ArrayList<Node>());
	}
	public Node breadthFirstSearch(int endX, int endY, Node[][] maze, List<Node> queue) {
		queue.add(maze[endX][endY]);
		while (queue.size() > 0) {
			Node node = queue.remove(0);
			int x = node.i;
			int y = node.j;
			if (canTraverse(x, y, maze)) {
				if (!maze[x+1][y].visited && maze[x+1][y].ch == ' ') {
					maze[x+1][y].visited = true;
					maze[x+1][y].prev = maze[x][y];
					queue.add(maze[x+1][y]);
				}
				if (!maze[x][y+1].visited && maze[x][y+1].ch == ' ') {
					maze[x][y+1].visited = true;
					maze[x][y+1].prev = maze[x][y];
					queue.add(maze[x][y+1]);
				}
				if (!maze[x-1][y].visited && maze[x-1][y].ch == ' ') {
					maze[x-1][y].visited = true;
					maze[x-1][y].prev = maze[x][y];
					queue.add(maze[x-1][y]);
				}
				if (!maze[x][y-1].visited && maze[x][y-1].ch == ' ') {
					maze[x][y-1].visited = true;
					maze[x][y-1].prev = maze[x][y];
					queue.add(maze[x][y-1]);
				}
			}
			else {
				if (maze[x][y].prev == maze[x-1][y]) {
						//maze[x-1][y].ch = ' ';
					maze[x][y].prev = null;
				}
				else if (maze[x][y].prev == maze[x][y-1]) {
					//maze[x][y-1].ch = ' ';
					maze[x][y].prev = null;
				}
				else if (maze[x][y].prev == maze[x+1][y]) {
						//maze[x+1][y].ch = ' ';
					maze[x][y].prev = null;
				}
				else {
						//maze[x][y+1].ch = ' ';
					maze[x][y].prev = null;
				}
			}
			if (maze[x][y].ch == 'S') break;
		}
		return maze[this.startX][this.startY];
	}
	
	public void breadthPath() {
		breadthPath(breadthFirstSearch());
	}
	
	public void breadthPath(Node node) {
		int x = node.i;
		int y = node.j;
		while (node.ch != 'E') {
			if (this.debug) System.out.println(this);
				
			if (maze[x+1][y].prev == maze[x][y]) {
				maze[x+1][y].ch = 'V';
			}
			else if (maze[x][y+1].prev == maze[x][y]) {
				maze[x][y+1].ch = 'V';
			}
			else if (maze[x-1][y].prev == maze[x][y]) {
				maze[x-1][y].ch = 'V';
			}
			else 
				maze[x][y-1].ch = 'V';
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(128);
		for (var j = 0; j < this.realDepth; j++) {
			for (var i = 0; i < this.realWidth; i++) {
				sb.append(this.maze[i][j].ch);
				if (i < this.realWidth-1) sb.append(" ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
