package cj.software.experiments.book.genetic.algorithms.chapter3;

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

	private static MazeField[][] toMazeArray(int[][] source)
	{
		MazeField[] entries = MazeField.values();
		int numRows = source.length;
		MazeField[][] result = new MazeField[numRows][];
		for (int row = 0; row < numRows; row++)
		{
			int numCols = source[row].length;
			result[row] = new MazeField[numCols];
			for (int col = 0; col < numCols; col++)
			{
				int sourceValue = source[row][col];
				result[row][col] = entries[sourceValue];
			}
		}
		return result;
	}

	public static void main(String[] args)
	{
		int[][] mazeInts = new int[][]
		{
				{ 0, 0, 0, 0, 1, 0, 1, 3, 2
				},
				{ 1, 0, 1, 1, 1, 0, 1, 3, 1
				},
				{ 1, 0, 0, 1, 3, 3, 3, 3, 1
				},
				{ 3, 3, 3, 1, 3, 1, 1, 0, 1
				},
				{ 3, 1, 3, 3, 3, 1, 1, 0, 0
				},
				{ 3, 3, 1, 1, 1, 1, 0, 1, 1
				},
				{ 1, 3, 0, 1, 3, 3, 3, 3, 3
				},
				{ 0, 3, 1, 1, 3, 1, 0, 1, 3
				},
				{ 1, 3, 3, 3, 3, 1, 1, 1, 4
				}
		};

		MazeField[][] fields = toMazeArray(mazeInts);

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
