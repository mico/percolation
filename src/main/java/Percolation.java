import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF sites;
    private final int n;
    private int[] openSites;

    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException("n cannot be less than 0");
        this.n = n;
        this.sites = new WeightedQuickUnionUF(n*n+4);
        openSites = new int[n*n];
    }
    public void open(int row, int col) {
        checkRowCol(row, col);
        if (row > 1 && isOpen(row - 1, col))
            sites.union(getPositionByRowCol(row - 1, col), getPositionByRowCol(row, col));
        if (row < n && isOpen(row + 1, col))
            sites.union(getPositionByRowCol(row + 1, col), getPositionByRowCol(row, col));
        if (col > 1 && isOpen(row , col - 1))
            sites.union(getPositionByRowCol(row, col - 1), getPositionByRowCol(row, col));
        if (col < n && isOpen(row, col + 1))
            sites.union(getPositionByRowCol(row, col + 1), getPositionByRowCol(row, col));

        if (row == 1)
            sites.union(getPositionByRowCol(row, col), 0);
        if (row == n && sites.find(1) != sites.find(0))
            sites.union(getPositionByRowCol(row, col), 1);

        openSites[getPositionByRowCol(row, col) - 2] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowCol(row, col);
        return openSites[getPositionByRowCol(row, col) - 2] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return sites.find(0) == sites.find(getPositionByRowCol(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.find(0) == sites.find(1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // check conversion between rowCol & sites & sitesOpen

    private int getPositionByRowCol(int row, int col) {
        return (row - 1) * n + col + 1;
    }

    private void checkRowCol(int row, int col) {
        if (col < 1)
            throw new IllegalArgumentException("column cannot be less than 1");
        if (row < 1)
            throw new IllegalArgumentException("row cannot be less than 1");

        if (row > n || col > n)
            throw new IllegalArgumentException("Out of bounds");
    }
}
