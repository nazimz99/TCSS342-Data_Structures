public class Node {
	char ch;
	boolean visited;
	boolean backtrack;
	boolean created; 
	Node prev;
	int i;
	int j;
	public Node(char ch, int i, int j) {
		this.ch = ch;
		this.i = i;
		this.j = j;
		this.visited = false;
		this.backtrack = false;
		this.created = false;
		this.prev = null;
	}
}
