package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import java.util.List;
import java.util.Map;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.City;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.CityPair;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;

public class Geometry
{
	public double calcDistance(City city1, City city2)
	{
		int x1 = city1.getX();
		int y1 = city1.getY();
		int x2 = city2.getX();
		int y2 = city2.getY();

		int deltaX = x2 - x1;
		int deltaY = y2 - y1;
		int deltaX2 = deltaX * deltaX;
		int deltaY2 = deltaY * deltaY;

		double result = Math.sqrt(deltaX2 + deltaY2);
		return result;
	}

	public double calcTotalDistance(
			Individual individual,
			List<City> cities,
			Map<CityPair, Double> existingDistances)
	{
		double result = 0;
		int numCities = cities.size();
		int[] chromosome = individual.getChromosome();
		for (int iCity = 0; iCity < numCities - 1; iCity++)
		{
			int index1 = chromosome[iCity];
			int index2 = chromosome[iCity + 1];
			double distance = calcDistance(individual, cities, existingDistances, index1, index2);
			result += distance;
		}
		int index1 = chromosome[numCities - 1];
		int index2 = chromosome[0];
		double distance = calcDistance(individual, cities, existingDistances, index1, index2);
		result += distance;
		individual.setDistance(result);
		return result;
	}

	private double calcDistance(
			Individual individual,
			List<City> cities,
			Map<CityPair, Double> existingDistances,
			int index1,
			int index2)
	{
		City city1 = cities.get(index1);
		City city2 = cities.get(index2);
		CityPair cityPair = new CityPair(city1, city2);
		double distance;
		if (existingDistances.containsKey(cityPair))
		{
			distance = existingDistances.get(cityPair);
		}
		else
		{
			distance = calcDistance(city1, city2);
			existingDistances.put(cityPair, distance);
		}
		return distance;
	}
}
