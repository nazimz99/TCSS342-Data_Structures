package evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class Genome implements Comparable<Genome>{
	private static String target = "CHRISTOPHER PAUL MARRIOTT";
	private List<Character> genome = new ArrayList<Character>();;
	private final char[] set = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y', 'Z','_', '-', '\''};
	private double mutationRate;
	private static final Random randalf = new Random();
	
	public Genome(double mutationRate) {
		genome.add('A');
		if (mutationRate >= 1 || mutationRate <= 0) {
			throw new IllegalArgumentException("Mutation rate must be within 0 and 1.");
		}
		this.mutationRate = mutationRate;
	}
	
	public Genome(Genome gene) {
	   this.genome = gene.genome;
	   this.mutationRate = gene.mutationRate;
	}	
	
	public static String getTarget() {
		return target;
	}
	
	public double getMutationRate() {
		return this.mutationRate;
	}
	
	public void mutate() {
		double chance = randalf.nextDouble();
		if (mutationRate >= chance) {
			Character ch = set[randalf.nextInt(set.length)];
			if (ch == '_') {
				ch = ' ';
			}
			int index = randalf.nextInt(genome.size() + 1);
			genome.add(index, ch);
		} 	
		chance = randalf.nextDouble();
		if (mutationRate >= chance && genome.size() >= 2) {
			int index = randalf.nextInt(genome.size());
			genome.remove(index);
		}
		for (int i = 0; i < genome.size(); i++) {
			chance = randalf.nextDouble();
			Character ch = set[randalf.nextInt(set.length)];
			if (ch == '_') {
				ch = ' ';
			}
			if (mutationRate >= chance) {
				genome.set(i, ch);
			}
		} 
	} 
	
	public void crossover(Genome other) {
		boolean crossover;
		int size;
		int low;
		List<Character> newList = new ArrayList<Character>();
		if (this.genome.size() > other.genome.size()) {
			size = this.genome.size();
			low = other.genome.size();
		}
		else {
			size = other.genome.size();
			low = this.genome.size();
		}		
		for (int i = 0; i < size; i++) {
			crossover = randalf.nextBoolean();
			if (crossover) {
				if (i < low || this.genome.size() == size) {
					newList.add(this.genome.get(i));
				}
				else {
					break;
				}
			}
			else {
				if (i < low || other.genome.size() == size) {
					newList.add(other.genome.get(i));
				}
				else {
					break;
				}
			}
		}
		this.genome = newList;
	} 
	
	public Integer fitness() {
		 int n = genome.size();
		 int m = target.length();
		 int l = Math.max(n, m); 
		 int f = Math.abs(m-n);
		 for (int i = 0; i < l; i++) {
			 if (i >= n || i >= m) {
				 f++;
			 }
			 else {
				 if (!genome.get(i).equals(target.charAt(i))) {
					 f++;
				 }
			 }
		 }
		 return f;
	} 
	
	public Integer Fitness() {
        int n = genome.size();
        int m = target.length();
        int D[][] = new int[n+1][m+1];
      
        for(int i = 0; i < m + 1; i++){
            D[0][i] = i;
        }
          
        for(int i = 0; i < n + 1; i++){
            D[i][0] = i;
        }
          
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < m + 1; j++){
                if(genome.get(i-1) == target.charAt(j-1)){
                    D[i][j] = D[i-1][j-1];
                }
                else{
                    D[i][j] = Math.min(D[i-1][j] + 1, Math.min(D[i][j-1] + 1, D[i-1][j-1] + 1));
                }
            }
        }
        return (D[n][m] + (Math.abs(n-m)+1)/2);
    } 

	@Override
	public String toString() {
        StringBuilder str = new StringBuilder(256);
        for (int i = 0; i < genome.size(); i++) {
            str.append(genome.get(i));
        }
        
        return "(\"" + str.toString() + "\", " + fitness() + ")";
    }

	@Override
	public int compareTo(Genome gene) {
		if (fitness() > gene.fitness()) {
			return 1;
		}
		else if (fitness() < gene.fitness()) {
			return -1;
		}
		else 
			return 0;
	}
}



