package cj.software.experiments.book.genetic.algorithms.chapter4.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CityPair
		implements
		Serializable
{
	static final long serialVersionUID = 1L;

	private final City city1;

	private final City city2;

	public CityPair(City city1, City city2)
	{
		this.city1 = city1;
		this.city2 = city2;
	}

	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder().append(this.city1).append(this.city2);
		int result = builder.build();
		return result;
	}

	@Override
	public boolean equals(Object otherObject)
	{
		boolean result;
		if (otherObject instanceof CityPair)
		{
			CityPair other = (CityPair) otherObject;
			EqualsBuilder builder = new EqualsBuilder().append(this.city1, other.city1)
					.append(this.city2, other.city2);
			result = builder.build();
		}
		else
		{
			result = false;
		}
		return result;
	}
}
