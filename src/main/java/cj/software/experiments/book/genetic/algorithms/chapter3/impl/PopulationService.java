package cj.software.experiments.book.genetic.algorithms.chapter3.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.apache.commons.lang3.builder.CompareToBuilder;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Population;

public class PopulationService
{
	private IndividualService individualService = new IndividualService();

	private Random random = new Random();

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
			individuals[index] = this.individualService.create(chromosomeLength);
		}
		Population result = new Population(individuals);
		return result;
	}

	public void rate(Population population)
	{
		Individual[] individuals = population.getIndividuals();
		Arrays.sort(individuals, new Comparator<Individual>()
		{
			@Override
			public int compare(Individual individual1, Individual individual2)
			{
				CompareToBuilder builder = new CompareToBuilder().append(
						individual2.getFitness(),
						individual1.getFitness());
				int result = builder.build();
				return result;
			}
		});
	}

	public void shuffle(Population population)
	{
		Individual[] individuals = population.getIndividuals();
		for (int i = 0; i < individuals.length - 1; i++)
		{
			int index = this.random.nextInt(i + 1);
			Individual toBeSwapped = individuals[index];
			individuals[index] = individuals[i];
			individuals[i] = toBeSwapped;
		}
	}

	public double calcPopulationFitness(Population population)
	{
		double result = 0.0;
		for (Individual individual : population.getIndividuals())
		{
			result += this.individualService.calcFitness(individual);
		}
		population.setPopulationFitness(result);
		return result;
	}

	public Population mutate(Population source, int elitismCount, double mutationRate)
	{
		int numIndividuals = source.size();
		Population result = create(numIndividuals);
		for (int populationIndex = 0; populationIndex < elitismCount; populationIndex++)
		{
			result.setIndividual(populationIndex, source.getIndividual(populationIndex));
		}
		for (int populationIndex = elitismCount; populationIndex < numIndividuals; populationIndex++)
		{
			Individual individual = source.getIndividual(populationIndex);
			int geneLength = individual.getChromosomeLength();
			for (int geneIndex = 0; geneIndex < geneLength; geneIndex++)
			{
				if (mutationRate > Math.random())
				{
					int newGene = 1 - individual.getGene(geneIndex);
					individual.setGene(geneIndex, newGene);
				}
			}
			result.setIndividual(populationIndex, individual);
		}
		return result;
	}
}
