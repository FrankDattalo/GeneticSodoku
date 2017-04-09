package shared;

/**
 * Interface for Local Search algorithms.
 * 
 * @author Frank Dattalo
 *
 * @param <T>
 *            The local search problem.
 * @param <K>
 *            The local search problem's state type.
 */
public interface Searcher<T extends Problem<K>, K> {

	public K search(T problem);
}
