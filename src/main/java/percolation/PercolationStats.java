package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int n, trials;
    private double[] results;
    private int total = 0;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
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
                // print("opened " + pc.numberOfOpenSites() + "\n");
            }
            // print("percolates " + i + "\n");
            results[i] = pc.numberOfOpenSites();
            total += pc.numberOfOpenSites();
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(results);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(results);
        // double mean = 0;
        // double sum = 0;
        // for (int i = 0; i < trials; i += 1) {
        //     double r = results[i];
        //     double delta = mean + ((double) r - mean) / (i + 1);
        //     sum += (r - mean) * (r - delta);
        //     mean = delta;
        // }
        // return Math.sqrt(sum / (trials - 1));
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
