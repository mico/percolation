import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private double results[];
    private int trials;
    private Percolation percolation;

    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("n shouldn't be negative or zero");
        if (trials <= 0) throw new IllegalArgumentException("trials shouldn't be negative or zero");

        results = new double[trials];
        this.trials = trials;
        for (int i = 1; i <= trials; i++) {
            percolation = new Percolation(n);
            int runs = 0;
            do {
                int row;
                int col;
                do {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                } while (percolation.isOpen(row, col));
                percolation.open(row, col);
                runs++;
            } while (!percolation.percolates());
            results[i-1] = (double) runs / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
//        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}
