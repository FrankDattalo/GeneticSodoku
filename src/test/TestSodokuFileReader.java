package test;

import java.io.File;

import shared.SodokuFileReader;

/**
 * Test case to test reading of boards from file.
 * @author Frank Dattalo
 */
public class TestSodokuFileReader {
	public static void main(String[] args) {
		test("./test/test1.txt");
	}
	
	public static void test(String fileName) {	
		int[][] board = SodokuFileReader.read(new File(fileName));
		
		printBoard(board);
	}
	
	public static void printBoard(int[][] board) {
		for(int y = board.length - 1; y >= 0; y--) {
			for(int x = 0; x < board.length; x++) {
				System.out.print(board[x][y]);
			}
			System.out.println();
		}
	}
}
