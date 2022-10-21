package cj.software.experiments.book.genetic.algorithms.chapter3.entity;

import java.io.Serializable;

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

	public Individual(int chromosomeLength)
	{
		this.chromosome = new int[chromosomeLength];
		for (int gene = 0; gene < chromosomeLength; gene++)
		{
			if (0.5 < Math.random())
			{
				this.setGene(gene, 1);
			}
			else
			{
				this.setGene(gene, 0);
			}
		}
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
		String fitnessFormatted = String.format("%12.8f", this.fitness);
		StringBuilder builder = new StringBuilder(fitnessFormatted).append("|").append(sb);
		String result = builder.toString();
		return result;
	}
}
