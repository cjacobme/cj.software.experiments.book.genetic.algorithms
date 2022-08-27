package cj.software.experiments.book.genetic.algorithms.chapter2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Population;
import cj.software.experiments.book.genetic.algorithms.chapter2.impl.PopulationService;

public class AllOnesGeeneticAlgorithm
{
	private static Logger logger = LogManager.getFormatterLogger();

	public static void main(String[] args)
	{
		PopulationService populationService = new PopulationService();

		double crossOverRate = 0.95;

		int elitismCount = 5;

		double mutationRate = 0.01;

		int chromosomeLength = 50;

		int populationSize = 100;

		Population population = populationService.create(populationSize, chromosomeLength);

		populationService.calcPopulationFitness(population);

		int generation = 1;

		while (!populationService.isTerminationConditionMet(population))
		{
			Individual fittest = populationService.getFittest(population, 0);
			logger.info("Best solution[%2d]: %s", generation, fittest);

			// apply crossover
			population = populationService.crossOver(population, crossOverRate, elitismCount);

			// apply mutation
			population = populationService.mutate(population, elitismCount, mutationRate);

			// evaluate population
			populationService.calcPopulationFitness(population);

			generation++;
		}

		logger.info("Found solution after %d cycles", generation);
		Individual fittest = populationService.getFittest(population, 0);
		logger.info("fittest is %s", fittest);
	}
}
