package shared;

import java.util.Collection;
import java.util.List;

/**
 * Local Search Problem interface.
 *
 * @author Frank Dattalo
 *
 * @param <T>
 *            - The state type of the problem.
 */
public interface Problem<T> {

	/**
	 * Returns the evaluation of this state.
	 *
	 * @param state
	 *            - the state to evaluate.
	 * @return The value associated with that state.
	 */
	public int evaluateState(T state);

	/**
	 * Returns the successors associated with this state.
	 *
	 * @param state
	 *            - the state to get the successors of.
	 * @return the successors.
	 */
	public Collection<T> getSuccessors(T state);

	/**
	 * @return The initial state of this problem.
	 */
	public T getInitialState();

	/**
	 * @param v
	 *            - the value to check.
	 * @return Returns whether the value v is a value that is 'good enough' to
	 *         terminate.
	 */
	public boolean terminatingValue(int v);

	/**
	 * @return Returns whether v1 is better than v2.
	 */
	public boolean betterValue(int v1, int v2);

	/**
	 * @param p
	 *            Sorts the collection based on their values where the best
	 *            value is the first element etc.
	 * @return The sorted list.
	 */
	public List<T> sortBestFirst(Collection<T> p);

	/**
	 * Reports whether the search should be terminated given the current value
	 * and the value of the best neighbor.
	 *
	 * @param current
	 *            - the current value.
	 * @param neighbor
	 *            - the neighbor value.
	 * @return Whether to stop the search.
	 */
	public boolean stopSearch(int current, int neighbor);
}
