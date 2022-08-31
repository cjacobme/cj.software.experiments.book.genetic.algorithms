package cj.software.experiments.book.genetic.algorithms.chapter3;

import static cj.software.experiments.book.genetic.algorithms.chapter3.entity.MazeField.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Maze;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.MazeField;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Population;
import cj.software.experiments.book.genetic.algorithms.chapter3.impl.PopulationService;

public class RobotController
{
	private static final Logger logger = LogManager.getFormatterLogger();

	public static final int MAX_GENERATIONS = 1000;

	public static void main(String[] args)
	{
		MazeField[][] fields = new MazeField[][]
		{
			//@formatter:off
			{EMPTY, EMPTY, EMPTY, EMPTY, WALL,  EMPTY, WALL,  ROUTE, START},
			{WALL,  EMPTY, WALL,  WALL,  WALL,  EMPTY, WALL,  ROUTE, WALL},
			{WALL,  EMPTY, EMPTY, WALL,  ROUTE, ROUTE, ROUTE, ROUTE, WALL},
			{ROUTE, ROUTE, ROUTE, WALL,  ROUTE, WALL,  WALL,  EMPTY, WALL},
			{ROUTE, WALL,  ROUTE, ROUTE, ROUTE, WALL,  WALL,  EMPTY, EMPTY},
			{ROUTE, ROUTE, WALL,  WALL,  WALL,  WALL,  EMPTY, WALL,  WALL},
			{WALL,  ROUTE, EMPTY, WALL,  ROUTE, ROUTE, ROUTE, ROUTE, ROUTE},
			{EMPTY, ROUTE, WALL,  WALL,  ROUTE, WALL,  EMPTY, WALL,  ROUTE},
			{WALL,  ROUTE, ROUTE, ROUTE, ROUTE, WALL,  WALL,  WALL,  GOAL}
			//@formatter:on

		};
		Maze maze = new Maze(fields);

		int populationSize = 200;
		double mutationRate = 0.05;
		double crossOverRate = 0.9;
		int eltitismCount = 2;
		int tournamentSize = 10;
		int chromosomeLength = 128;

		PopulationService populationService = new PopulationService();
		Population population = populationService.create(populationSize, chromosomeLength);

		// evaluate population
		populationService.calcPopulationFitness(population, maze);
		populationService.rate(population);

		int generationCounter = 1;
		while (!populationService.isTerminationConditionMet(generationCounter, MAX_GENERATIONS))
		{
			Individual fittest = population.getIndividual(0);
			logger.info("Best solution[%4d]: %s", generationCounter, fittest);

			// TODO apply cross over

			// TODO apply mutation
			populationService.mutate(population, eltitismCount, mutationRate);

			// evaluate population
			populationService.calcPopulationFitness(population, maze);
			populationService.rate(population);

			generationCounter++;
		}
	}
}
