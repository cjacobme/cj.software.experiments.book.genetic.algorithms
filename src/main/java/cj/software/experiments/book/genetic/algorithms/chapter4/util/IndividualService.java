package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import java.util.Random;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;

public class IndividualService
{
	private Random random = new Random();

	public Individual create(int chromosomeLength)
	{
		int[] chromosomes = new int[chromosomeLength];
		for (int chromo = 0; chromo < chromosomeLength; chromo++)
		{
			chromosomes[chromo] = chromo;
		}

		for (int chromo = 0; chromo < chromosomeLength; chromo++)
		{
			int pos1 = this.random.nextInt(chromosomeLength);
			int pos2 = this.random.nextInt(chromosomeLength);
			int swap = chromosomes[pos1];
			chromosomes[pos1] = chromosomes[pos2];
			chromosomes[pos2] = swap;
		}

		Individual result = new Individual(chromosomes);
		return result;
	}

	public void mutate(Individual source, double mutationRate)
	{
		int[] chromosomes = source.getChromosome();
		for (int chromo = 0; chromo < chromosomes.length; chromo++)
		{
			if (Math.random() < mutationRate)
			{
				int otherIndex = (int) (Math.random() * chromosomes.length);
				int swap = chromosomes[chromo];
				chromosomes[chromo] = chromosomes[otherIndex];
				chromosomes[otherIndex] = swap;
			}
		}
	}
}
