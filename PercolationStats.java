
import edu.princeton.cs.algs4.StdRandom;

import java.util.stream.IntStream;

import static edu.princeton.cs.algs4.StdOut.print;


public class PercolationStats {
    private int n, trials;
    private Percolation pc;
    private int results[];
    private int total = 0;
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        this.n = n;
        this.trials = trials;

        results = new int[trials];
        collectData();
    }

    private void collectData() {
        IntStream.range(0, trials).forEachOrdered((i) -> {
            pc = new Percolation(n);
            while (!pc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                pc.open(row, col);
                // print("opened " + pc.numberOfOpenSites() + "\n");
            }
            print("percolates " + i + "\n");
            results[i] = pc.numberOfOpenSites();
            total += pc.numberOfOpenSites();
        });
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return (float) (total / trials) / (n*n);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return 0.1;
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return 0.1;
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return 0.1;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        print("mean: " + ps.mean());
    }
}
