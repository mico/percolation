import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int n, trials;
    private double mean, stddev = 0;
    private double[] results;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N or trials outside its prescribed range");
        }

        this.n = n;
        this.trials = trials;

        results = new double[trials];
        collectData();

    }

    private void collectData() {
        Percolation pc;
        for (int i = 0; i < trials; i++) {
            pc = new Percolation(n);
            while (!pc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                pc.open(row, col);
            }
            results[i] = (double) pc.numberOfOpenSites() / (n * n);
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        if (mean == 0) {
            mean = StdStats.mean(results);
        }
        return mean;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        if (stddev == 0) {
            stddev = StdStats.stddev(results);
        }
        return stddev;
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.print("mean: " + ps.mean() + "\n");
        System.out.print("stddev: " + ps.stddev() + "\n");
        System.out.print("confidenceLo: " + ps.confidenceLo() + "\n");
        System.out.print("confidenceHi: " + ps.confidenceHi() + "\n");
    }
}
