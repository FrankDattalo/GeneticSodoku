package shared;

/**
 * The representation of a Board position.
 *
 * @author Frank Dattalo
 */
public final class Position {
	private final int xIndex;
	private final int yIndex;
	private final int value;

	/**
	 * @param p
	 *            - The original position.
	 * @param value
	 *            - the new value.
	 * @return the new position object with value value.
	 */
	public static Position mapNewValue(Position p, int value) {
		return new Position(p.xIndex, p.yIndex, value);
	}

	/**
	 * @param value
	 *            - the new value.
	 * @return the new position object with value value.
	 */
	public Position mapNewValue(int value) {
		return mapNewValue(this, value);
	}

	/**
	 * Instantiates a position object.
	 *
	 * @param x
	 *            - the x coordinate
	 * @param y
	 *            - the y coordinate
	 * @param val
	 *            - the value of the position
	 */
	public Position(int x, int y, int val) {
		xIndex = x;
		yIndex = y;
		value = val;
	}

	/**
	 * @return Returns the value at this position.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return Returns the x index.
	 */
	public int getX() {
		return xIndex;
	}

	/**
	 * @return Returns the y index.
	 */
	public int getY() {
		return yIndex;
	}

	@Override
	public String toString() {
		return "(" + (xIndex + 1) + ", " + (yIndex + 1) + "): " + value;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof Position)) {
			return false;
		}

		final Position p = (Position) other;

		boolean ret = p.xIndex == xIndex;
		ret = ret && p.yIndex == yIndex;
		ret = ret && p.value == value;

		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + xIndex;
		result = prime * result + yIndex;
		result = prime * result + value;

		return result;
	}
}
