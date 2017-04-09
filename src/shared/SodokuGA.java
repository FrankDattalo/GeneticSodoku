package shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sodoku Genetic Algorithm Local Search Problem implementation.
 *
 * @author Frank Dattalo
 */
public final class SodokuGA extends Sodoku implements GAProblem<List<Position>> {

	private final int maxConflicts;

	/**
	 * Public constructor.
	 *
	 * @param b
	 *            the file location of the board.
	 */
	public SodokuGA(String b) {
		super(b);
		final int dimension = getBoard().getDimension();
		final int maxConflictsPerPiece = (dimension - 1) * 3;
		final int maxPieces = dimension * 2;
		maxConflicts = maxConflictsPerPiece * maxPieces;
	}

	/**
	 * @param initialPopulationSize
	 *            - The initial population size to create.
	 * @return Returns a population of size initial population size.
	 */
	@Override
	public Collection<List<Position>> getInitialPopulation(int initialPopulationSize) {
		final List<List<Position>> ret = new ArrayList<>();
		for (int i = 0; i < initialPopulationSize; i++) {
			ret.add(getInitialState());
		}
		return ret.stream().filter(l -> !l.isEmpty()).collect(Collectors.toList());
	}

	/**
	 * @param state
	 *            - The state to evaluate.
	 * @return Returns the fitness value for the state
	 */
	@Override
	public int fitnessFunction(List<Position> state) {
		return maxConflicts - evaluateState(state);
	}

	/**
	 * @param state
	 *            - the state to evaluate.
	 * @return whether this state is fit enough to stop searching
	 */
	@Override
	public boolean isFitEnough(List<Position> state) {
		return evaluateState(state) == 0;
	}

	/**
	 * @param population
	 *            - the population to search.
	 * @return Returns the most fit individual for the population.
	 */
	@Override
	public List<Position> mostFitIndividual(Collection<List<Position>> population) {
		return population.stream().sorted((a, b) -> fitnessFunction(b) - fitnessFunction(a)).findFirst()
				.orElseGet(ArrayList::new);
	}

	/**
	 * @return The mutation rate for this problem.
	 */
	@Override
	public double mutationRate() {
		return .3;
	}

	/**
	 * @param individual
	 *            - the individual to mutate.
	 * @return an individual which was mutated based off of the input.
	 */
	@Override
	public List<Position> mutate(List<Position> individual) {
		final List<Position> copy = copy(individual);

		final int index1 = randomIndex(copy);

		int value = getRandomNumber();
		final int currentValue = copy.get(index1).getValue();

		while (value == currentValue) {
			value = getRandomNumber();
		}

		copy.set(index1, copy.get(index1).mapNewValue(value));

		return copy;
	}

	/**
	 * Reproduces x and y to create a child.
	 *
	 * @param x
	 *            - parent 1.
	 * @param y
	 *            - parent 2.
	 * @return the child.
	 */
	@Override
	public List<Position> reproduce(List<Position> x, List<Position> y) {
		final int cutoff = randomIndex(x);

		final List<Position> child = new ArrayList<>();

		for (int i = 0; i < x.size(); i++) {
			final List<Position> toGetFrom = i <= cutoff ? x : y;

			child.add(toGetFrom.get(i));
		}

		return child;
	}

	/**
	 * Randomly selects an individual proportionally to its fitness function.
	 *
	 * @param population
	 *            - the population to search.
	 * @return the randomly selected individual.
	 */
	@Override
	public List<Position> proportionalSelect(Collection<List<Position>> populationCol) {
		final List<List<Position>> population = populationCol.stream().collect(Collectors.toList());
		final List<Integer> fitness = population.stream().map(this::fitnessFunction).collect(Collectors.toList());
		final int totalFitness = fitness.stream().reduce(0, (i, j) -> i + j);
		double currentStart = 0.0;
		final List<Double> fitnessPercents = fitness.stream().map((f) -> (double) f / totalFitness)
				.collect(Collectors.toList());

		final double random = Math.random();

		for (int i = 0; i < population.size(); i++) {
			final double fitnessPercent = fitnessPercents.get(i);
			if (currentStart <= random && random <= currentStart + fitnessPercent) {
				return population.get(i);
			}
			currentStart += fitnessPercent;
		}

		throw new RuntimeException("This should never happen");
	}

	private static int randomIndex(List<Position> ps) {
		return (int) Math.floor(Math.random() * ps.size());
	}

	/**
	 * @return Returns a new, empty population.
	 */
	@Override
	public Collection<List<Position>> newPopulation() {
		return new ArrayList<>();
	}
}
