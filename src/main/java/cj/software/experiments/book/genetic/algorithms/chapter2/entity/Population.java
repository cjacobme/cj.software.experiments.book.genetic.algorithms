package cj.software.experiments.book.genetic.algorithms.chapter2.entity;

import java.io.Serializable;

public class Population
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	private Individual individuals[];

	private double populationFitness = -1;

	public Population(Individual[] individuals)
	{
		this.individuals = individuals;
	}

	public double getPopulationFitness()
	{
		return this.populationFitness;
	}

	public void setPopulationFitness(double populationFitness)
	{
		this.populationFitness = populationFitness;
	}

	public Individual[] getIndividuals()
	{
		return this.individuals;
	}

	public int size()
	{
		return this.individuals.length;
	}

	public Individual setIndividual(int index, Individual individual)
	{
		return this.individuals[index] = individual;
	}

	public Individual getIndividual(int index)
	{
		return this.individuals[index];
	}
}
