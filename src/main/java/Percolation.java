import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private static final int firstIndex = 2;
    private final int length;
    private boolean[] openSites;
    private int numberOpenSites = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        uf = new WeightedQuickUnionUF(n*n + 2);
        length = n;
        openSites = new boolean[n*n];
    }

    private int positionByRowCol(int row, int col) {
        return firstIndex + length * (row - 1) + (col - 1);
    }

    private int[] findOpenAround(int row, int col) {
        int[] openAround = new int[4];
        int currentIndex = 0;
        if (col > 1 && openSites[positionByRowCol(row, col-1) - firstIndex]) {
            openAround[currentIndex] = positionByRowCol(row, col-1);
            // System.out.println("return pos " + positionByRowCol(row, col-1));
            currentIndex += 1;
        }
        if (col < length && openSites[positionByRowCol(row, col+1) - firstIndex]) {
            openAround[currentIndex] = positionByRowCol(row, col+1);
            // System.out.println("return pos " + positionByRowCol(row, col-1));
            currentIndex += 1;
        }
        if (row > 1 && openSites[positionByRowCol(row-1, col) - firstIndex]) {
            openAround[currentIndex] = positionByRowCol(row-1, col);
            currentIndex += 1;
        }
        if (row < length && openSites[positionByRowCol(row+1, col) - firstIndex]) {
            openAround[currentIndex] = positionByRowCol(row+1, col);
            currentIndex += 1;
        }
        int[] res = new int[currentIndex];
        System.arraycopy(openAround, 0, res, 0, currentIndex);
        return res;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) { return; }
        int p = positionByRowCol(row, col);
        // System.out.println("row " + row + " col " + col);
        openSites[p - firstIndex] = true;
        // System.out.println("open " + p);
        if (row == 1) {
            // System.out.println("union " + p + " 0");
            uf.union(p, 0);
        }
        if (row == length) {
            // System.out.println("union " + p + " 1");
            uf.union(p, 1);
        }
        for (int q : findOpenAround(row, col)) {
            // System.out.println("union " + p + " " + q);
            uf.union(p, q);
        }
        // System.out.println("bottom to: " + uf.find(1));
        numberOpenSites += 1;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openSites[positionByRowCol(row, col) - firstIndex];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.connected(positionByRowCol(row, col), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, 1);
    }

    public static void main(String[] args) {
        // no tests here
    }
}
