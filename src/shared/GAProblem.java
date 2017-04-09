package shared;

import java.util.Collection;

/**
 * Genetic Algorithm Problem interface.
 *
 * @author Frank Dattalo
 *
 * @param <T>
 *            The state type of this problem.
 */
public interface GAProblem<T> extends Problem<T> {

	/**
	 * @return Returns a new, empty population.
	 */
	public Collection<T> newPopulation();

	/**
	 * @param initialPopulationSize
	 *            - The initial population size to create.
	 * @return Returns a population of size initial population size.
	 */
	public Collection<T> getInitialPopulation(int initialPopulationSize);

	/**
	 * @param state
	 *            - The state to evaluate.
	 * @return Returns the fitness value for the state
	 */
	public int fitnessFunction(T state);

	/**
	 * @param state
	 *            - the state to evaluate.
	 * @return whether this state is fit enough to stop searching
	 */
	public boolean isFitEnough(T state);

	/**
	 * @param population
	 *            - the population to search.
	 * @return Returns the most fit individual for the population.
	 */
	public T mostFitIndividual(Collection<T> population);

	/**
	 * @return The mutation rate for this problem.
	 */
	public double mutationRate();

	/**
	 * @param individual
	 *            - the individual to mutate.
	 * @return an individual which was mutated based off of the input.
	 */
	public T mutate(T individual);

	/**
	 * Reproduces x and y to create a child.
	 *
	 * @param x
	 *            - parent 1.
	 * @param y
	 *            - parent 2.
	 * @return the child.
	 */
	public T reproduce(T x, T y);

	/**
	 * Randomly selects an individual proportionally to its fitness function.
	 * 
	 * @param population
	 *            - the population to search.
	 * @return the randomly selected individual.
	 */
	public T proportionalSelect(Collection<T> population);
}
