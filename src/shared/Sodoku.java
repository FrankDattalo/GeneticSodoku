package shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the Sodoku Local Search Problem.
 *
 * @author Frank Dattalo
 */
public class Sodoku implements Problem<List<Position>> {

	private final Board board;

	/**
	 * Public constructor.
	 *
	 * @param b
	 *            - The file location of the board.
	 */
	public Sodoku(String b) {
		board = Board.create(b);
	}

	/**
	 * Returns the evaluation of this state.
	 *
	 * @param state
	 *            - the state to evaluate.
	 * @return The value associated with that state.
	 */
	@Override
	public int evaluateState(List<Position> state) {
		int ret = 0;

		final Board toEvaluate = board.mergeWithVector(state);

		final int partitionSize = (int) Math.sqrt(toEvaluate.getDimension());

		final int length = toEvaluate.getDimension();

		for (int y = length - 1; y >= 0; y--) {
			for (int x = 0; x < length; x++) {
				ret += countOfConflicts(toEvaluate, x, y, length, partitionSize);
			}
		}

		return ret;
	}

	/**
	 * Returns the successors associated with this state.
	 *
	 * @param state
	 *            - the state to get the successors of.
	 * @return the successors.
	 */
	@Override
	public Collection<List<Position>> getSuccessors(List<Position> state) {
		final List<List<Position>> ret = new ArrayList<>();

		for (int k = 0; k < state.size(); k++) {
			final int oldVal = state.get(k).getValue();
			for (int i = 1; i <= 4; i++) {
				if (oldVal != i) {
					final List<Position> stateCopy = copy(state);
					stateCopy.set(k, stateCopy.get(k).mapNewValue(i));
					ret.add(stateCopy);
				}
			}
		}

		return ret;
	}

	/**
	 * @return The initial state of this problem.
	 */
	@Override
	public List<Position> getInitialState() {
		return board.mapToVector().stream().map(i -> i.mapNewValue(getRandomNumber())).collect(Collectors.toList());
	}

	/**
	 * @param v
	 *            - the value to check.
	 * @return Returns whether the value v is a value that is 'good enough' to
	 *         terminate.
	 */
	@Override
	public boolean terminatingValue(int v) {
		return v == 0;
	}

	/**
	 * @return Returns whether v1 is better than v2.
	 */
	@Override
	public List<List<Position>> sortBestFirst(Collection<List<Position>> p) {
		return p.stream().sorted((a, b) -> evaluateState(a) - evaluateState(b)).collect(Collectors.toList());
	}

	/**
	 * @param p
	 *            Sorts the collection based on their values where the best
	 *            value is the first element etc.
	 * @return The sorted list.
	 */
	@Override
	public boolean betterValue(int v1, int v2) {
		return v1 < v2;
	}

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
	@Override
	public boolean stopSearch(int current, int neighbor) {
		return neighbor >= current;
	}

	/**
	 * @return The board representation.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return a random number between 1 and board.dimension()
	 */
	protected int getRandomNumber() {
		return (int) Math.ceil((Math.random() * board.getDimension()));
	}

	/**
	 * Copies the state l.
	 *
	 * @param l
	 *            the state to copy.
	 * @return a copy of l.
	 */
	protected static List<Position> copy(List<Position> l) {
		final List<Position> ret = new ArrayList<>();
		l.stream().forEach(ret::add);
		return ret;
	}

	/**
	 * Calcuates the partition's start and end given a position and partation
	 * size.
	 *
	 * @param position
	 *            the starting position.
	 * @param partitionSize
	 *            the partition size.
	 * @return (x, y) dimensions
	 */
	private static int[] calculatePartitionDimensions(int position, int partitionSize) {
		final int partitionStart = (position / partitionSize) * partitionSize;
		final int partitionEnd = partitionStart + partitionSize;
		return new int[] { partitionStart, partitionEnd };
	}

	/**
	 * Counts the number of conflicts a position x, y.
	 * 
	 * @param b
	 *            The board to analyze.
	 * @param x
	 *            the x position.
	 * @param y
	 *            the y position.
	 * @param length
	 *            the length of the board.
	 * @param partitionSize
	 *            the partition size of the board.
	 * @return the number of conflicts.
	 */
	private static int countOfConflicts(Board b, int x, int y, int length, int partitionSize) {
		int count = 0;

		final int value = b.getValue(x, y);

		for (int x1 = 0; x1 < length; x1++) {
			if (x != x1 && value == b.getValue(x1, y)) {
				count++;
			}
		}

		for (int y1 = 0; y1 < length; y1++) {
			if (y != y1 && value == b.getValue(x, y1)) {
				count++;
			}
		}

		int[] partition = calculatePartitionDimensions(x, partitionSize);
		final int xStart = partition[0];
		final int xEnd = partition[1];

		partition = calculatePartitionDimensions(y, partitionSize);
		final int yStart = partition[0];
		final int yEnd = partition[1];

		for (int x1 = xStart; x1 < xEnd; x1++) {
			for (int y1 = yStart; y1 < yEnd; y1++) {
				if (x1 != x && y1 != y && value == b.getValue(x1, y1)) {
					count++;
				}
			}
		}

		return count;
	}
}
