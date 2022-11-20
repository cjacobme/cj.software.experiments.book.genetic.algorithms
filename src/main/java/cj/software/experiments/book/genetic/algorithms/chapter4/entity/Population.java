package cj.software.experiments.book.genetic.algorithms.chapter4.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Population
		implements
		Serializable
{
	static final long serialVersionUID = 1L;

	private Individual[] individuals;

	private double populationFitness;

	public Population(Population source)
	{
		this.individuals = Arrays.copyOf(source.individuals, source.individuals.length);
		this.populationFitness = source.populationFitness;
	}

	public Population(Individual[] individuals)
	{
		this.individuals = individuals;
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
