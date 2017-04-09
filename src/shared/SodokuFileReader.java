package shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * File Reader for Sodoku Board.
 *
 * @author Frank Dattalo
 */
public final class SodokuFileReader {

	/**
	 * Reads from file f and returns a grid of board positions.
	 * 
	 * @param f
	 *            the file to read from.
	 * @return the grid of values.
	 */
	public static int[][] read(File f) {

		int[][] ret = null;

		Scanner in = null;
		try {
			in = new Scanner(f);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}

		final int size = Integer.parseInt(in.nextLine());

		ret = new int[size][size];

		for (int lineNumber = size - 1; lineNumber >= 0; lineNumber--) {
			final String line = in.nextLine();
			for (int linePosition = 0; linePosition < size; linePosition++) {
				final char current = line.charAt(linePosition);
				int num = 0;
				if (current >= '0' && current <= '9') {
					num = current - '0';
				}
				ret[linePosition][lineNumber] = num;
			}
		}

		in.close();

		return ret;
	}
}
