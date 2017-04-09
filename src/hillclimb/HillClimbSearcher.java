package hillclimb;

import java.util.Collection;
import java.util.List;

import shared.Problem;
import shared.Searcher;

/**
 * Hill climb search algorithm implementation.
 * 
 * @author Frank Dattalo
 *
 * @param <T>
 *            The type of the problem
 * @param <K>
 *            The problem's state type
 */
public final class HillClimbSearcher<T extends Problem<K>, K> implements Searcher<T, K> {

	private final int maxIterations;

	private int totalIterations = 0;

	public HillClimbSearcher(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	@Override
	public K search(T problem) {
		int iterations = 0;

		K best = null;

		while (true) {
			final K current = searchHC(problem);

			if (best == null) {
				best = current;
			}

			final int bestVal = problem.evaluateState(best);
			final int currentVal = problem.evaluateState(current);

			if (problem.betterValue(currentVal, bestVal)) {
				best = current;
			} else if (problem.terminatingValue(bestVal) || iterations == maxIterations) {
				return best;
			} else {
				iterations++;
			}

			System.out.println("Restarting Search");
		}
	}

	private K searchHC(T problem) {
		K current = problem.getInitialState();

		while (true) {
			System.out.println();
			System.out.println("Iterations : " + totalIterations);

			final Collection<K> neighborsC = problem.getSuccessors(current);

			if (neighborsC.isEmpty()) {
				return current;
			}

			final List<K> neighbors = problem.sortBestFirst(neighborsC);

			final K neighbor = neighbors.get(0);

			final int evaluateN = problem.evaluateState(neighbor);
			final int evaluateC = problem.evaluateState(current);

			if (!problem.betterValue(evaluateN, evaluateC)) {
				return current;
			}

			current = neighbor;

			totalIterations++;

			System.out.println("Chosen     : " + current);
			System.out.println("Value      : " + evaluateN);
		}
	}
}
