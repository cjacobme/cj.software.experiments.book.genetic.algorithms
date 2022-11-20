package cj.software.experiments.book.genetic.algorithms.chapter4.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Individual
		implements
		Serializable
{
	static final long serialVersionUID = 1L;

	private int[] chromosome;

	private double fitnessValue;

	private double distance;

	public Individual(int[] chromosomes)
	{
		this.chromosome = chromosomes;
	}

	public int[] getChromosome()
	{
		return this.chromosome;
	}

	public double getFitnessValue()
	{
		return this.fitnessValue;
	}

	public void setFitnessValue(double fitnessValue)
	{
		this.fitnessValue = fitnessValue;
	}

	public double getDistance()
	{
		return this.distance;
	}

	public void setDistance(double distance)
	{
		this.distance = distance;
	}

	public int getChromosomeLength()
	{
		return this.chromosome.length;
	}

	public int getGene(int pos)
	{
		return this.chromosome[pos];
	}

	public void setGene(int pos, int value)
	{
		this.chromosome[pos] = value;
	}

	public boolean containsGene(int gene)
	{
		boolean result = false;
		for (int i = 0; i < this.chromosome.length; i++)
		{
			if (this.chromosome[i] == gene)
			{
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int chromosomeLength = this.chromosome.length;
		for (int chromo = 0; chromo < chromosomeLength - 1; chromo++)
		{
			sb.append(this.chromosome[chromo]).append(",");
		}
		sb.append(this.chromosome[chromosomeLength - 1]);
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("order", sb);
		String result = builder.build();
		return result;
	}
}
