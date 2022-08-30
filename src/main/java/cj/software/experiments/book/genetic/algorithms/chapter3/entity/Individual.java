package cj.software.experiments.book.genetic.algorithms.chapter3.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Individual
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	private int[] chromosome;

	private double fitness = -1;

	public Individual(int[] chromosome)
	{
		this.chromosome = chromosome;
	}

	public int[] getChromosome()
	{
		return this.chromosome;
	}

	public int getChromosomeLength()
	{
		return this.chromosome.length;
	}

	public void setGene(int index, int value)
	{
		this.chromosome[index] = value;
	}

	public int getGene(int index)
	{
		return this.chromosome[index];
	}

	public double getFitness()
	{
		return this.fitness;
	}

	public void setFitness(double fitness)
	{
		this.fitness = fitness;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int gene : this.chromosome)
		{
			sb.append(gene);
		}
		String fitnessFormatted = String.format("%6.3f", this.fitness);
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(sb)
				.append(fitnessFormatted);
		String result = builder.build();
		return result;
	}
}
