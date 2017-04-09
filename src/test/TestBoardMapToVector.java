package test;

import shared.Board;

/**
 * Test case to test boards being mapped to state vectors.
 * @author Frank Dattalo
 */
public class TestBoardMapToVector {
	public static void main(String[] args) {
		Board board = Board.create("./test/test1.txt");
		System.out.println(board);
		System.out.println(board.mapToVector());
	}
}
