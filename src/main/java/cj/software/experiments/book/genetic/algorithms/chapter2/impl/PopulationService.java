package cj.software.experiments.book.genetic.algorithms.chapter2.impl;

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

	public boolean isTerminationConditionMet(Population population)
	{
		boolean result = false;
		for (Individual individual : population.getIndividuals())
		{
			if (individual.getFitness() >= 1.0)
			{
				result = true;
				break;
			}
		}
		return result;
	}

	public Individual selectParent(Population population)
	{
		Individual[] individuals = population.getIndividuals();
		double populationFitness = population.getPopulationFitness();
		double rouletteWheelPosition = Math.random() * populationFitness;
		double spinWheel = 0;
		Individual result = null;
		for (Individual individual : individuals)
		{
			spinWheel += individual.getFitness();
			if (spinWheel >= rouletteWheelPosition)
			{
				result = individual;
				break;
			}
		}
		if (result == null)
		{
			result = individuals[individuals.length - 1];
		}
		return result;
	}

	public Population crossOver(Population source, double crossOverRate, int elitismCount)
	{
		int numPopulations = source.size();
		Population result = create(numPopulations);
		for (int populationIndex = 0; populationIndex < numPopulations; populationIndex++)
		{
			Individual parent1 = getFittest(source, populationIndex);
			if (crossOverRate > Math.random() && populationIndex > elitismCount)
			{
				int geneLength = parent1.getChromosomeLength();
				Individual offspring = this.individualService.create(geneLength);
				Individual parent2 = selectParent(source);
				for (int geneIndex = 0; geneIndex < geneLength; geneIndex++)
				{
					if (0.5 > Math.random())
					{
						offspring.setGene(geneIndex, parent1.getGene(geneIndex));
					}
					else
					{
						offspring.setGene(geneIndex, parent2.getGene(geneIndex));
					}
				}
				result.setIndividual(populationIndex, offspring);
			}
			else
			{
				result.setIndividual(populationIndex, parent1);
			}
		}
		return result;
	}
}
