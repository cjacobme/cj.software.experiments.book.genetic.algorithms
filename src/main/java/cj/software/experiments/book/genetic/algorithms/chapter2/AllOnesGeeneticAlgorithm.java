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

		double crossOverRate = 0.95;

		int elitismCount = 5;

		double mutationRate = 0.01;

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
				100,
				mutationRate,
				crossOverRate,
				elitismCount);

		Population population = geneticAlgorithm.initPopulation(50);

		populationService.calcPopulationFitness(population);

		int generation = 1;

		while (!populationService.isTerminationConditionMet(population))
		{
			Individual fittest = populationService.getFittest(population, 0);
			// TODO use logger
			System.out.println(String.format("Best solution[%2d]: %s", generation, fittest));

			// apply crossover
			population = populationService.crossOver(population, crossOverRate, elitismCount);

			// apply mutation
			population = populationService.mutate(population, elitismCount, mutationRate);

			// evaluate population
			populationService.calcPopulationFitness(population);

			generation++;
		}

		System.out.println("Found solution after " + generation + " cycles");
		Individual fittest = populationService.getFittest(population, 0);
		System.out.println("fittest is " + fittest);
	}
}
