package cj.software.experiments.book.genetic.algorithms.chapter3.impl;

import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Maze;
import cj.software.experiments.book.genetic.algorithms.chapter3.entity.Robot;

public class IndividualService
{
	private MazeService mazeService = new MazeService();

	public Individual create(int chromosomeLength)
	{
		int[] chromosomes = new int[chromosomeLength];
		for (int gene = 0; gene < chromosomeLength; gene++)
		{
			if (0.5 < Math.random())
			{
				chromosomes[gene] = 1;
			}
			else
			{
				chromosomes[gene] = 0;
			}
		}
		Individual result = new Individual(chromosomes);
		return result;
	}

	public double calcFitness(Individual individual, Maze maze)
	{
		int[] chromosome = individual.getChromosome();
		Robot robot = new Robot(chromosome, maze, 100);
		robot.run();
		int fitness = this.mazeService.scoreRoute(maze, robot.getRoute());
		individual.setFitness(fitness);
		return fitness;
	}
}
