package cj.software.experiments.book.genetic.algorithms.chapter4.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.CompareToBuilder;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.City;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.CityPair;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Population;

public class Rating
{
	private Geometry geometry = new Geometry();

	public double calcFitness(
			Individual individual,
			List<City> cities,
			Map<CityPair, Double> existingDistances)
	{
		double distanceSum = this.geometry.calcTotalDistance(individual, cities, existingDistances);
		double result = 1 / distanceSum;
		individual.setFitnessValue(result);
		return result;
	}

	public double calcPopulationFitness(
			Population population,
			List<City> cities,
			Map<CityPair, Double> existingDistances)
	{
		double result = 0;

		for (Individual individual : population.getIndividuals())
		{
			double individualFitness = this.calcFitness(individual, cities, existingDistances);
			result += individualFitness;
		}

		population.setPopulationFitness(result);
		return result;
	}

	public List<Individual> rate(Population population)
	{
		List<Individual> result = Arrays.asList(population.getIndividuals());
		Collections.sort(result, new Comparator<Individual>()
		{

			@Override
			public int compare(Individual individual1, Individual individual2)
			{
				CompareToBuilder builder = new CompareToBuilder().append(
						individual2.getFitnessValue(),
						individual1.getFitnessValue());
				int result = builder.build();
				return result;
			}
		});
		return result;
	}
}
