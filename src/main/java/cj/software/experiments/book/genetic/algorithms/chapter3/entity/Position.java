package cj.software.experiments.book.genetic.algorithms.chapter3.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Position
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	private int x;
	private int y;

	public Position(int x, int y)
	{
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

	public void decrX()
	{
		this.x = Math.max(this.x - 1, 0);
	}

	public void decrY()
	{
		this.y = Math.max(this.y - 1, 0);
	}

	public void incrX(int max)
	{
		this.x = Math.min(this.x + 1, max);
	}

	public void incrY(int max)
	{
		this.y = Math.min(this.y + 1, max);
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
		if (otherObject instanceof Position)
		{
			Position other = (Position) otherObject;
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

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append(this.x)
				.append(this.y);
		String result = builder.build();
		return result;
	}
}
