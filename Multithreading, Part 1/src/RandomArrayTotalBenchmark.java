import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

/**
 * Demonstrates basic multithreading, and illustrates how to break up a problem
 * into subproblems. Also used to motivate the inefficiency of constantly
 * creating new threads instead of reusing them.
 *
 * <p><em>
 * Note that this class is designed to illustrate a specific concept, and
 * may not be an example of proper class design outside of this context.
 * </em></p>
 */
@RunWith(Parameterized.class)
@AxisRange(min = 0, max = 0.25)
@BenchmarkMethodChart(filePrefix = "benchmark-array")
public class RandomArrayTotalBenchmark extends AbstractBenchmark {

	private final int size;
	private final int threads;

	public RandomArrayTotalBenchmark(int size, int threads) {
		this.size = size;
		this.threads = threads;
	}

	@Parameters(name = "size = {0}, threads = {1}")
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{100000, 1}, {1000000, 1}, {10000000, 1},
				{100000, 2}, {1000000, 2}, {10000000, 2},
				{100000, 5}, {1000000, 5}, {10000000, 5}
		};

		return Arrays.asList(data);
	}

	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5, clock = Clock.NANO_TIME)
	@Test
	public void testSingle() throws Exception {
		int[] numbers = new int[size];
		RandomArrayTotal.fillRandom(numbers, 100);
		RandomArrayTotal.total(numbers);
	}

	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5, clock = Clock.NANO_TIME)
	@Test
	public void testMultiPool() throws Exception {
		int[] numbers = new int[size];
		RandomArrayTotal.fillRandom(numbers, 100);
		RandomArrayTotal.total(numbers, threads);
	}

}
