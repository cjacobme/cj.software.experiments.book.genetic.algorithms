package cj.software.experiments.book.genetic.algorithms.chapter2;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.GeneticAlgorithm;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Population;

public class AllOnesGeeneticAlgorithm
{
	public static void main(String[] args)
	{
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.95, 0);

		Population population = geneticAlgorithm.initPopulation(50);
	}
}
