// package percolation;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n, trials;
    private Percolation pc;
    private int[] results;
    private int total = 0;
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        this.n = n;
        this.trials = trials;

        results = new int[trials];
        collectData();
    }

    private void collectData() {
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
        return ((double) total / trials) / (n*n);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        double mean = 0;
        double sum = 0;
        for (int i = 0; i < trials; i += 1) {
            double r = results[i];
            double delta = mean + ((double) r - mean) / (i + 1);
            sum += (r - mean) * (r - delta);
            mean = delta;
        }
        return Math.sqrt(sum / (trials - 1));
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.print("mean: " + ps.mean() + "\n");
        System.out.print("stddev: " + ps.stddev() + "\n");
        System.out.print("confidenceLo: " + ps.confidenceLo() + "\n");
        System.out.print("confidenceHi: " + ps.confidenceHi() + "\n");
    }
}
