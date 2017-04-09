package genetic;

import java.util.Collection;

import shared.GAProblem;
import shared.Searcher;

/**
 * Genetic Algorithm implementation.
 *
 * @author Frank Dattalo
 *
 * @param <T>
 *            the problem type
 * @param <K>
 *            the problem's state type
 */
public final class GeneticAlgorithmSearcher<T extends GAProblem<K>, K> implements Searcher<T, K> {

	private final int pop;

	private int totalIterations = 0;

	GeneticAlgorithmSearcher(int pop) {
		this.pop = pop;
	}

	@Override
	public K search(T problem) {
		final double mutationRate = problem.mutationRate();

		Collection<K> population = problem.getInitialPopulation(pop);

		while (true) {
			System.out.println();
			System.out.println("Iterations : " + totalIterations);

			final Collection<K> newPop = problem.newPopulation();

			for (int i = 0; i < population.size(); i++) {
				final K parent1 = problem.proportionalSelect(population);
				final K parent2 = problem.proportionalSelect(population);

				K child = problem.reproduce(parent1, parent2);

				if (Math.random() <= mutationRate) {
					child = problem.mutate(child);
				}

				newPop.add(child);
			}

			population = newPop;

			final K mostFit = problem.mostFitIndividual(population);

			totalIterations++;
			System.out.println("Chosen     : " + mostFit);
			System.out.println("Fitness    : " + problem.fitnessFunction(mostFit));

			if (problem.isFitEnough(mostFit)) {
				return mostFit;
			}
		}
	}
}
