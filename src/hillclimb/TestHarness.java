package hillclimb;

import java.util.List;

import shared.Position;
import shared.Sodoku;

public final class TestHarness {
	public static void runTest(String fileName) {
		final Sodoku s = new Sodoku(fileName);
		final HillClimbSearcher<Sodoku, List<Position>> hc = new HillClimbSearcher<>(10);
		final List<Position> state = hc.search(s);

		System.out.println();
		System.out.printf("Final value: %d\n", s.evaluateState(state));
		System.out.println("Board:");
		System.out.println(s.getBoard().mergeWithVector(state));
	}
}
