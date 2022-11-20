package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Population;

public class PopulationService
{
	private IndividualService individualService = new IndividualService();

	public Population create(int populationSize, int numCities)
	{
		Individual[] individuals = new Individual[populationSize];
		for (int pop = 0; pop < populationSize; pop++)
		{
			individuals[pop] = this.individualService.create(numCities);
		}
		Population result = new Population(individuals);
		return result;
	}
}
