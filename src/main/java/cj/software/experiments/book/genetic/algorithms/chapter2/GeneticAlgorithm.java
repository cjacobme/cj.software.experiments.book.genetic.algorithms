package cj.software.experiments.book.genetic.algorithms.chapter2;

import java.io.Serializable;

public class GeneticAlgorithm
		implements
		Serializable
{
	static final long serialVersionUID = 1L;

	private int populationSize;

	private double mutationRate;

	private double crossoverRate;

	private int elitismCount;

	public GeneticAlgorithm(
			int populationSize,
			double mutationRate,
			double crossoverRate,
			int elitismCount)
	{
		super();
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
	}

	public int getPopulationSize()
	{
		return this.populationSize;
	}

	public double getMutationRate()
	{
		return this.mutationRate;
	}

	public double getCrossoverRate()
	{
		return this.crossoverRate;
	}

	public int getElitismCount()
	{
		return this.elitismCount;
	}

}
