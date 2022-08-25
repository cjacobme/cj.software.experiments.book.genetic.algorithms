package cj.software.experiments.book.genetic.algorithms.chapter2.impl;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Population;

public class PopulationService
{
	private IndividualGenerator individualGenerator = new IndividualGenerator();

	public Population create(int populationSize)
	{
		Individual[] individuals = new Individual[populationSize];
		Population result = new Population(individuals);
		return result;
	}

	public Population create(int populationSize, int chromosomeLength)
	{
		Individual[] individuals = new Individual[populationSize];
		for (int index = 0; index < populationSize; index++)
		{
			individuals[index] = this.individualGenerator.create(chromosomeLength);
		}
		Population result = new Population(individuals);
		return result;
	}

	public Individual getFittest(Population population, int index)
	{
		Individual[] individuals = population.getIndividuals();
		Arrays.sort(individuals, new Comparator<Individual>()
		{

			@Override
			public int compare(Individual individual1, Individual individual2)
			{
				CompareToBuilder builder = new CompareToBuilder().append(
						individual1.getFitness(),
						individual2.getFitness());
				int result = builder.build();
				return result;
			}
		});
		return individuals[index];
	}
}
