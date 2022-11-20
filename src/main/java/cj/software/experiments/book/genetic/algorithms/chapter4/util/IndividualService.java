package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;

public class IndividualService
{
	private Random random = new Random();

	public Individual create(int chromosomeLength)
	{
		Set<Integer> entries = new HashSet<>(chromosomeLength);
		for (int index = 0; index < chromosomeLength; index++)
		{
			entries.add(index);
		}
		int[] chromosomes = new int[chromosomeLength];
		for (int chromo = 0; chromo < chromosomeLength; chromo++)
		{
			int index = this.random.nextInt(entries.size());
			entries.remove(index);
			chromosomes[chromo] = index;
		}
		Individual result = new Individual(chromosomes);
		return result;
	}
}
