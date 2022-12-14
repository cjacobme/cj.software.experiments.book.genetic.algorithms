package cj.software.experiments.book.genetic.algorithms.chapter2.impl;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Individual;
import cj.software.experiments.book.genetic.algorithms.chapter2.entity.Population;

public class PopulationServiceTest
{
	private static PopulationService populationService;;

	@BeforeAll
	public static void createService()
	{
		populationService = new PopulationService();
	}

	@Test
	public void rate1()
	{
		Individual zero = new Individual(new int[]
		{ 0, 0, 0, 0, 0
		});
		zero.setFitness(0.0);
		Individual four = new Individual(new int[]
		{ 1, 0, 1, 1, 1
		});
		four.setFitness(0.8);
		Individual one = new Individual(new int[]
		{ 0, 1, 0, 0, 0
		});
		one.setFitness(0.2);
		Individual[] individuals = new Individual[]
		{ zero, four, one
		};
		Population population = new Population(individuals);

		populationService.rate(population);

		Individual fit0 = population.getIndividual(0);
		SoftAssertions softy = new SoftAssertions();
		softy.assertThat(fit0).as("fit(0)").isSameAs(four);
		Individual fit1 = population.getIndividual(1);
		softy.assertThat(fit1).as("fit(1)").isSameAs(one);
		Individual fit2 = population.getIndividual(2);
		softy.assertThat(fit2).as("fit(2)").isSameAs(zero);
		softy.assertAll();
	}
}
