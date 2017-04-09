package test;

import java.util.Scanner;

/**
 * Program which sets up either hill climbing or genetic algorithm search from standard input.
 * @author Frank Dattalo
 */
public class StdinTestHarness {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("hc/ga?> ");
		
		String line = in.nextLine();
		boolean hc = false;
		
		if(line.equals("hc")) {
			hc = true;
		} else if (!line.equals("ga")) {
			System.err.println("Invalid option");
			in.close();
			System.exit(1);
		}
		
		System.out.print("file location?> ");
		line = in.nextLine();
		
		if(hc) {
			hillclimb.TestHarness.runTest(line);
		} else {
			genetic.TestHarness.runTest(line);
		}
		
		in.close();
	}
}
