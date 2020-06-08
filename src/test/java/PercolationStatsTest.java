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
        assert ps.mean() >= 0.55 : String.format("Should be higher then 0.55 (%f)", ps.mean());
        assert ps.mean() <= 0.7 : String.format("Should be lower then 0.7 (%f)", ps.mean());
    }

    @Test
    public void shouldReturnStddevInAcceptableRange() {
        PercolationStats ps = new PercolationStats(2, 10000);
        assert ps.stddev() > 0.0 : String.format("Should be higher then 0.0 (%f)", ps.stddev());
        assert ps.stddev() <= 0.2 : String.format("Should be lower then 0.2 (%f)", ps.stddev());
    }

    @Test
    public void shouldReturnConfidenceLoInAcceptableRange() {
        PercolationStats ps = new PercolationStats(2, 10000);
        assert ps.confidenceLo() > 0.55 : String.format("Should be higher then 0.0 (%f)", ps.confidenceLo());
        assert ps.confidenceLo() <= 0.7 : String.format("Should be lower then 0.2 (%f)", ps.confidenceLo());
    }

    @Test
    public void shouldReturnConfidenceHiInAcceptableRange() {
        PercolationStats ps = new PercolationStats(2, 10000);
        assert ps.confidenceHi() > 0.55 : String.format("Should be higher then 0.0 (%f)", ps.confidenceHi());
        assert ps.confidenceHi() <= 0.7 : String.format("Should be lower then 0.2 (%f)", ps.confidenceHi());
    }


}
