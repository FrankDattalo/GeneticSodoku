package genetic;

import java.util.List;

import shared.Position;
import shared.SodokuGA;

public final class TestHarness {
	public static void runTest(String fileName) {
		final SodokuGA problem = new SodokuGA(fileName);
		final GeneticAlgorithmSearcher<SodokuGA, List<Position>> s = new GeneticAlgorithmSearcher<>(10);
		final List<Position> state = s.search(problem);

		System.out.println();
		System.out.printf("Fitness value: %d\n", problem.fitnessFunction(state));
		System.out.println("Board:");
		System.out.println(problem.getBoard().mergeWithVector(state));
	}
}
