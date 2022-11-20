package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.City;

public class CityService
{
	public List<City> createCities(int numCities)
	{
		Set<City> asSet = createCitiesSet(numCities);
		List<City> result = new ArrayList<>(asSet);
		return result;
	}

	private Set<City> createCitiesSet(int numCities)
	{
		Set<City> result = new HashSet<>();
		while (result.size() < numCities)
		{
			int xPos = (int) (100 * Math.random());
			int yPos = (int) (100 * Math.random());
			City city = new City(xPos, yPos);
			if (!result.contains(city))
			{
				result.add(city);
			}
		}
		return result;
	}

}
