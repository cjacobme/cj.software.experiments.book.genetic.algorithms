package cj.software.experiments.book.genetic.algorithms.chapter4.entity;

import java.io.Serializable;

public class Population
		implements
		Serializable
{
	static final long serialVersionUID = 1L;

	private Individual[] individuals;

	private double populationFitness;

	public Population(int populationSize, int chromosomeLength)
	{
		this.individuals = new Individual[populationSize];
		for (int index = 0; index < populationSize; index++)
		{
			this.individuals[index] = new Individual(chromosomeLength);
		}
	}

	public Individual[] getIndividuals()
	{
		return this.individuals;
	}

	public double getPopulationFitness()
	{
		return this.populationFitness;
	}

	public void setPopulationFitness(double populationFitness)
	{
		this.populationFitness = populationFitness;
	}

}
