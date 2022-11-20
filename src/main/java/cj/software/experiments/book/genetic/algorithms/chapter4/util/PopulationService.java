package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Population;

public class PopulationService
{
	private final IndividualService individualService = new IndividualService();

	private final Rating rating = new Rating();

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

	public Population crossOver(
			Population source,
			double crossOverRate,
			int elitismCount,
			int tournamentSize)
	{
		List<Individual> sourceIndividuals = this.rating.rate(source);
		int numIndividuals = sourceIndividuals.size();
		Individual[] newIndividuals = new Individual[numIndividuals];
		for (int individual = 0; individual < numIndividuals; individual++)
		{
			Individual parent1 = sourceIndividuals.get(individual);
			if (Math.random() < crossOverRate && individual >= elitismCount)
			{
				Individual parent2 = selectParent(source, tournamentSize);
				int[] offspringChromosomes = new int[parent1.getChromosomeLength()];
				Arrays.fill(offspringChromosomes, -1);
				Individual offspring = new Individual(offspringChromosomes);

				int subPos1 = (int) (Math.random() * parent1.getChromosomeLength());
				int subPos2 = (int) (Math.random() * parent1.getChromosomeLength());
				final int start = Math.min(subPos1, subPos2);
				final int end = Math.max(subPos1, subPos2);
				for (int i = start; i < end; i++)
				{
					offspring.setGene(i, parent1.getGene(i));
				}
				int chromosomeLength = parent2.getChromosomeLength();
				for (int chromo = 0; chromo < chromosomeLength; chromo++)
				{
					int parent2Index = chromo + end;
					if (parent2Index >= chromosomeLength)
					{
						parent2Index -= chromosomeLength;
					}
					int parent2Gene = parent2.getGene(parent2Index);
					if (!offspring.containsGene(parent2Gene))
					{
						for (int pos = 0; pos < offspring.getChromosomeLength(); pos++)
						{
							if (offspring.getGene(pos) == -1)
							{
								offspring.setGene(pos, parent2Gene);
								break;
							}
						}
					}
				}
				newIndividuals[individual] = new Individual(offspringChromosomes);
			}
			else
			{
				newIndividuals[individual] = parent1;
			}
		}

		Population result = new Population(newIndividuals);
		return result;
	}

	private Individual selectParent(Population source, int tournamentSize)
	{
		Individual[] shuffled = shuffle(source.getIndividuals());
		Individual[] tournament = new Individual[tournamentSize];
		for (int i = 0; i < tournamentSize; i++)
		{
			tournament[i] = shuffled[i];
		}
		Population temp = new Population(tournament);
		List<Individual> rated = this.rating.rate(temp);
		Individual result = rated.get(0);
		return result;
	}

	private Individual[] shuffle(Individual[] individuals)
	{
		Individual[] result = Arrays.copyOf(individuals, individuals.length);
		Random rnd = new Random();
		for (int i = individuals.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			Individual a = result[index];
			result[index] = result[i];
			result[i] = a;
		}
		return result;
	}

}
