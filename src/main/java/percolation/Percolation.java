package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int first_index = 2;
    private int length;
    private boolean[] openSites;
    private int numberOpenSites = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        uf = new WeightedQuickUnionUF(n*n + 2);
        length = n;
        openSites = new boolean[n*n];
    }

    private int positionByRowCol(int row, int col) {
        return first_index + length * (row - 1) + (col - 1);
    }

    private ArrayList<Integer> findOpenAround(int row, int col) {
        ArrayList<Integer> openAround = new ArrayList<Integer>();
        if (col > 1 && openSites[positionByRowCol(row, col-1) - first_index]) {
            openAround.add(positionByRowCol(row, col-1));
        }
        if (col < length && openSites[positionByRowCol(row, col+1) - first_index]) {
            openAround.add(positionByRowCol(row, col+1));
        }
        if (row > 1 && openSites[positionByRowCol(row-1, col) - first_index]) {
            openAround.add(positionByRowCol(row-1, col));
        }
        if (row < length && openSites[positionByRowCol(row+1, col) - first_index]) {
            openAround.add(positionByRowCol(row+1, col));
        }
        return openAround;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) { return; }
        int p = positionByRowCol(row, col);
        openSites[p - first_index] = true;
        // System.out.print("open " + row + " " + col + "\n");
        if (row == 1) {
            // System.out.print("union p q " + p + " " + 0 + "\n");
            uf.union(p, 0);
        }
        if (row == length) {
            // System.out.print("union p q " + p + " " + 1 + "\n");
            uf.union(p, 1);
        }
        findOpenAround(row, col).forEach((q) -> {
            // System.out.print("union p q " + p + " " + q + "\n");
            uf.union(p, q);
        });
        numberOpenSites += 1;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        //print("open? " + openSites[positionByRowCol(row, col) - first_index] + "\n");
        return openSites[positionByRowCol(row, col) - first_index];
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

    }
}
