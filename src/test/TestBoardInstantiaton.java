package test;

import shared.Board;

/**
 * Test case to test board creation.
 * @author Frank Dattalo
 */
public class TestBoardInstantiaton {
	public static void main(String[] args) {
		System.out.println(Board.create("./test/test1.txt"));
	}
}
