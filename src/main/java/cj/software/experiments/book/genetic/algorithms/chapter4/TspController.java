package cj.software.experiments.book.genetic.algorithms.chapter4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cj.software.experiments.book.genetic.algorithms.chapter4.entity.City;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.CityPair;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter4.entity.Population;
import cj.software.experiments.book.genetic.algorithms.chapter4.util.CityService;
import cj.software.experiments.book.genetic.algorithms.chapter4.util.PopulationService;
import cj.software.experiments.book.genetic.algorithms.chapter4.util.Rating;

public class TspController
{
	public static final int NUM_CITIES = 100;

	public static final int POPULATION_SIZE = 100;

	public static final int MAX_GENERATIONS = 10000;

	private final CityService cityService = new CityService();

	private final Rating rating = new Rating();

	private PopulationService populationService = new PopulationService();

	private final Logger logger = LogManager.getFormatterLogger();

	public static void main(String[] args)
	{
		TspController controller = new TspController();
		controller.runTsp();
	}

	private void runTsp()
	{
		List<City> cities = this.cityService.createCities(NUM_CITIES);
		Population population = this.populationService.create(POPULATION_SIZE, NUM_CITIES);
		Map<CityPair, Double> existingDistances = new HashMap<>();
		this.rating.calcPopulationFitness(population, cities, existingDistances);
		List<Individual> rated = this.rating.rate(population);
		for (int generation = 0; generation < MAX_GENERATIONS; generation++)
		{
			Individual best = rated.get(0);
			this.logger.info("G#%04d best distance=%8.2f %s", generation, best.getDistance(), best);

			population = this.populationService.crossOver(population, 0.9, 2, 5);
			this.rating.calcPopulationFitness(population, cities, existingDistances);
			rated = this.rating.rate(population);
		}
	}
}
