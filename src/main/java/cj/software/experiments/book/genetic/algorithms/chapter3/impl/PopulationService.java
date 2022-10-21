package cj.software.experiments.book.genetic.algorithms.chapter3.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.apache.commons.lang3.builder.CompareToBuilder;

import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Maze;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Population;

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

	public double calcPopulationFitness(Population population, Maze maze)
	{
		double result = 0.0;
		for (Individual individual : population.getIndividuals())
		{
			result += this.individualService.calcFitness(individual, maze);
		}
		population.setPopulationFitness(result);
		return result;
	}

	public Population mutate(Population source, int elitismCount, double mutationRate)
	{
		int numIndividuals = source.size();
		Population result = create(numIndividuals);
		for (int populationIndex = 0; populationIndex < numIndividuals; populationIndex++)
		{
			Individual individual = source.getIndividual(populationIndex);
			if (populationIndex >= elitismCount)
			{
				int geneLength = individual.getChromosomeLength();
				for (int geneIndex = 0; geneIndex < geneLength; geneIndex++)
				{
					if (mutationRate > Math.random())
					{
						int newGene = 1 - individual.getGene(geneIndex);
						individual.setGene(geneIndex, newGene);
					}
				}
			}
			result.setIndividual(populationIndex, individual);
		}
		return result;
	}

	public Individual selectParent(Population population, int tournamentSize)
	{
		Population tournament = new Population(tournamentSize);
		this.shuffle(population);
		for (int index = 0; index < tournamentSize; index++)
		{
			Individual individual = population.getIndividual(index);
			tournament.setIndividual(index, individual);
		}
		rate(tournament);
		Individual result = tournament.getIndividual(0);
		return result;
	}

	public Population crossover(
			Population source,
			double crossoverRate,
			int elitismCount,
			int tournamentSize)
	{
		int numIndividuals = source.size();
		Population result = new Population(numIndividuals);
		for (int populationIndex = 0; populationIndex < numIndividuals; populationIndex++)
		{
			Individual parent1 = source.getIndividual(populationIndex);
			if (crossoverRate > Math.random() && populationIndex >= elitismCount)
			{
				int numGenes = parent1.getChromosomeLength();
				Individual offspring = new Individual(numGenes);
				Individual parent2 = this.selectParent(source, tournamentSize);
				int swapPoint = (int) (Math.random() * (numGenes + 1));
				for (int geneIndex = 0; geneIndex < swapPoint; geneIndex++)
				{
					offspring.setGene(geneIndex, parent1.getGene(geneIndex));
				}
				for (int geneIndex = swapPoint; geneIndex < numGenes; geneIndex++)
				{
					offspring.setGene(geneIndex, parent2.getGene(geneIndex));
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

	public boolean isTerminationConditionMet(int generationsCount, int maxGenerations)
	{
		boolean result = (generationsCount > maxGenerations);
		return result;
	}
}
