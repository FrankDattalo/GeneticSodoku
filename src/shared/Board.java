package shared;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Board class to be used throughout the program.
 *
 * @author Frank Dattalo
 */
public final class Board {

	/**
	 * Representation
	 */
	private final int[][] board;

	/**
	 * Creates a new board where the list of positions within the state vector
	 * is mapped onto the board.
	 *
	 * @param stateVector
	 *            - the state vector to map onto the board.
	 * @return A new board with the state vector mapped to it.
	 */
	public Board mergeWithVector(List<Position> stateVector) {
		return mergeWithVector(this, stateVector);
	}

	/**
	 * Creates a new board where the list of positions within the state vector
	 * is mapped onto the board.
	 *
	 * @param b
	 *            - the board to merge with the state vector.
	 * @param stateVector
	 *            - the state vector to map onto the board.
	 * @return A new board with the state vector mapped to it.
	 */
	public static Board mergeWithVector(Board b, List<Position> stateVector) {
		final int[][] newBoard = deepCopy(b.board);

		for (final Position item : stateVector) {
			newBoard[item.getX()][item.getY()] = item.getValue();
		}

		return new Board(newBoard);
	}

	/**
	 * @return The dimension of the board.
	 */
	public int getDimension() {
		return board.length;
	}

	/**
	 * @param b
	 *            - A board representation to copy.
	 * @return Returns a new board representation that is a deep copy of b.
	 */
	private static int[][] deepCopy(int[][] b) {
		final int[][] ret = new int[b.length][b.length];

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				ret[i][j] = b[i][j];
			}
		}

		return ret;
	}

	/**
	 * Creates a board from a given file.
	 *
	 * @param file
	 *            - the file to create from.
	 * @return The instantiated board.
	 */
	public static Board create(String file) {
		return Board.create(SodokuFileReader.read(new File(file)));
	}

	/**
	 * Creates a new board given a board representation.
	 *
	 * @param board
	 *            - the inner representation.
	 * @return A new Board.
	 */
	private static Board create(int[][] board) {
		return new Board(board);
	}

	/**
	 * Private constructor.
	 *
	 * @param board
	 *            - The board representation.
	 */
	private Board(int[][] board) {
		this.board = board;
	}

	/**
	 * Returns the board value at (x, y).
	 *
	 * @param x
	 *            - the x coordinate
	 * @param y
	 *            - the y coordinate
	 * @return the board value at (x, y)
	 */
	public int getValue(int x, int y) {
		return board[x][y];
	}

	/**
	 * Maps this board to a state vector.
	 *
	 * @return The state vector representing this board.
	 */
	public List<Position> mapToVector() {
		final ArrayList<Position> ret = new ArrayList<>();

		for (int y = board.length - 1; y >= 0; y--) {
			for (int x = 0; x < board.length; x++) {
				if (board[x][y] == 0) {
					ret.add(new Position(x, y, board[x][y]));
				}
			}
		}

		return ret;
	}

	@Override
	public String toString() {
		String ret = "";
		for (int y = board.length - 1; y >= 0; y--) {
			for (final int[] element : board) {
				ret = ret + element[y];
			}
			ret = ret + System.lineSeparator();
		}
		return ret;
	}
}
