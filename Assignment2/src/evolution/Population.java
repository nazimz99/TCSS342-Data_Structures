package evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Population {
	private List<Genome> population = new ArrayList<Genome>();
	private Genome mostFit;
	private Random randalf;
	private Double mutationRate;
	private Integer numGenomes;
	
	public Population(Integer numGenomes, Double mutationRate) {
		randalf = new Random();
		this.numGenomes = numGenomes;
		if (mutationRate >= 1 && mutationRate <= 0) {
			throw new IllegalArgumentException("Mutation rate must be within 0 and 1.");
		}
		this.mutationRate = mutationRate;
		for (int i = 0; i < this.numGenomes; i++) {
			population.add(new Genome(this.mutationRate));
		}
		mostFit = population.get(0);
	}
	
	public void day() {
		int mid = 0;
		boolean crossover;
		Genome clone;
		Genome gene;
		mid = numGenomes/2;
		Collections.sort(population);
		mostFit = population.get(0);
		for (int i = 0; i < mid; i++) {
			population.remove(population.size() - 1);
		}
		while (population.size() < numGenomes) {
			crossover = randalf.nextBoolean();
			if (crossover) {
				gene = population.get(randalf.nextInt(population.size()));
			    clone = new Genome(gene);
				clone.mutate();
				population.add(clone);
			}
			else {
				gene = population.get(randalf.nextInt(population.size()));
				Genome other = population.get(randalf.nextInt(population.size()));
				clone = new Genome(gene);
				clone.crossover(other);
				clone.mutate();
				population.add(clone);
			}
			if (clone.fitness() < mostFit.fitness()) {
				mostFit = clone;
			}
		} 
	} 
	
	public int size() {
		return this.numGenomes;
	}

	public Genome getMostFit() {
		return this.mostFit;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(256);
		sb.append("(" + "\"");
		for (int i = 0; i < population.size(); i++) {
			sb.append(population.get(i) + ", ");
		}
		sb.append(this.mostFit.fitness() + ")");
		return sb.toString();
	} 

}

