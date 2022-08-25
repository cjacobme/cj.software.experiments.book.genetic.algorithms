package cj.software.experiments.book.genetic.algorithms.chapter2;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.GeneticAlgorithm;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Population;
import cj.software.experiments.book.genetic.algorithms.chapter2.impl.PopulationService;

public class AllOnesGeeneticAlgorithm
{
	public static void main(String[] args)
	{
		PopulationService populationService = new PopulationService();

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.95, 0);

		Population population = geneticAlgorithm.initPopulation(50);

		populationService.calcPopulationFitness(population);

		int generation = 1;

		while (!populationService.isTerminationConditionMet(population))
		{
			Individual fittest = populationService.getFittest(population, 0);
			// TODO use logger
			System.out.println(String.format("Best solution[%2d]: %s", generation, fittest));

			// TODO apply crossover

			// TODO apply mutation

			// evaluate population
			populationService.calcPopulationFitness(population);
		}

		System.out.println("Found solution after " + generation + " cycles");
		Individual fittest = populationService.getFittest(population, 0);
		System.out.println("fittest is " + fittest);
	}
}
