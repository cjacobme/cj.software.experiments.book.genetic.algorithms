package cj.software.experiments.book.genetic.algorithms.chapter4.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class City
		implements
		Serializable
{
	static final long serialVersionUID = 1L;

	private final int x;

	private final int y;

	public City(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("x", this.x)
				.append("y", this.y);
		String result = builder.build();
		return result;
	}

	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder().append(this.x).append(this.y);
		int result = builder.build();
		return result;
	}

	@Override
	public boolean equals(Object otherObject)
	{
		boolean result;
		if (otherObject instanceof City)
		{
			City other = (City) otherObject;
			EqualsBuilder builder = new EqualsBuilder().append(this.x, other.x)
					.append(this.y, other.y);
			result = builder.build();
		}
		else
		{
			result = false;
		}
		return result;
	}
}
