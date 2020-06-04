import edu.princeton.cs.algs4.StdStats;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertTrue;

import java.lang.instrument.Instrumentation;

public class PercolationStatsTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForNegativeN() {
        PercolationStats ps = new PercolationStats(-1, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForNegativeTrials() {
        PercolationStats ps = new PercolationStats(1, -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForZeroN() {
        PercolationStats ps = new PercolationStats(0, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForZeroTrials() {
        PercolationStats ps = new PercolationStats(1, 0);
    }

    @Test
    public void shouldReturnMeanInAcceptableRange() {
        PercolationStats ps = new PercolationStats(2, 10000);
        assertTrue(ps.mean() >= 0.6, "Should be higher then 0.6");
        assertTrue(ps.mean() <= 0.7, "Should be lower then 0.7");
    }
}
