package cj.software.experiments.book.genetic.algorithms.chapter2.entity;

import java.io.Serializable;

import cj.software.experiments.book.genetic.algorithms.chapter2.impl.PopulationService;

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

	// TODO this is service implementation in an entity and should be moved to a service class
	public Population initPopulation(int chromosomeLength)
	{
		PopulationService populationService = new PopulationService();
		Population population = populationService.create(this.populationSize, chromosomeLength);
		return population;
	}
}
