package evolution;

public class Main {
	private static Genome gene1;
	private static Genome gene2;
	private static Genome gene3;
	private static Population p;
	private static int days;
	
	public static void main(String[] args)  {
		
        testGenome(); 
        testPopulation(); 

        p = new Population(100, .05);
        days = 0;
        long startTime = System.currentTimeMillis();
        while (p.getMostFit().fitness() > 0) {
            p.day();
            System.out.println(p.getMostFit());
            days++;
        }
            long endTime = System.currentTimeMillis();
            System.out.println("Generations: " + days );
            System.out.println("Running Time: "+ (endTime - startTime) + " milliseconds"); 
    }

	public static void testGenome() {
		System.out.println("These lines are for testing the Genome class.");
		gene1 = new Genome(0.35);
		gene2 = new Genome(0.05);
		gene3 = new Genome(gene1);
		System.out.println("Testing copy constructor: ");
		System.out.println(gene1 + ")" + ", " + gene1.getMutationRate());
		System.out.println(gene3 + ")" + ", " + gene3.getMutationRate());
		gene3 = new Genome(gene2);
		System.out.println("Testing copy constructor again: ");
		System.out.println(gene2 + ")" + ", " + gene2.getMutationRate());
		System.out.println(gene3 + ")" + ", " + gene3.getMutationRate());
		gene3 = new Genome(0.45);
		System.out.println("\n" + "Testing mutate method: ");
		for (int i = 0; i < 10; i ++) {
			gene1.mutate();
			System.out.println(gene1 + ")");
		}
		System.out.println("\n");
		for (int i = 0; i < 10; i ++) {
			gene2.mutate();
			System.out.println(gene2 + ")");
		}
		System.out.println("\n");
		for (int i = 0; i < 10; i ++) {
			gene3.mutate();
			System.out.println(gene3 + ")");
		}
		System.out.println("\n Testing crossover method: ");
		System.out.println(gene1 + ")");
		System.out.println(gene3 + ")");
		gene1.crossover(gene3);
		System.out.println(gene1 + ")");
		System.out.println(gene1.fitness());
	}
	
	public static void testPopulation() {
		p = new Population(100, 0.05);
		System.out.println(p.getMostFit() + ")");
		System.out.println("\n");
		for (int i = 0; i < 10; i++) {
			p.day();
			System.out.println(p.getMostFit() + ")");
		}
	}
}