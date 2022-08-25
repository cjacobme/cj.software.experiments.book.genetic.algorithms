package cj.software.experiments.book.genetic.algorithms.chapter2.impl;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Individual;

public class IndividualGenerator
{
	public Individual create(int chromosomeLength)
	{
		int[] chromosomes = new int[chromosomeLength];
		for (int gene = 0; gene < chromosomeLength; gene++)
		{
			if (0.5 < Math.random())
			{
				chromosomes[gene] = 1;
			}
			else
			{
				chromosomes[gene] = 0;
			}
		}
		Individual result = new Individual(chromosomes);
		return result;
	}
}
