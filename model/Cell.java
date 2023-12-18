package model;

import java.util.ArrayList;

// Represents a cell in the Game of Life grid.
public class Cell {
    private Point point;
    private boolean state;
    private ArrayList<Point> neighbors;

    // Constructor to create a cell with specified coordinates, grid dimensions, and initial state.
    public Cell(int x, int y, int rows, int cols) {
        this(x, y, rows, cols, false);
    }

    // Overloaded constructor to create a cell with specified coordinates, grid dimensions, and state.
    public Cell(int x, int y, int rows, int cols, boolean state) {
        if (isValidCell(x, y, rows, cols)) {
            this.point = new Point(x, y);
        }
        this.state = state;
        initializeNeighbors(rows, cols);
    }

    // Getter method for the cell's coordinates.
    public Point getPoint() {
        return point;
    }

    // Setter method for the cell's coordinates.
    public void setPoint(Point point) {
        this.point = point;
    }

    // Getter method for the cell's state.
    public boolean isState() {
        return state;
    }

    // Setter method for the cell's state.
    public void setState(boolean state) {
        this.state = state;
    }

    // Getter method for the cell's neighbors.
    public ArrayList<Point> getNeighbors() {
        return neighbors;
    }

    // Setter method for the cell's neighbors.
    public void setNeighbors(ArrayList<Point> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    // Initializes the neighbors of the cell based on grid dimensions.
    private void initializeNeighbors(int rows, int cols) {
        this.neighbors = new ArrayList<>();
        // Relative coordinates of possible 8 neighbors
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = this.point.getX() + dx[i];
            int ny = this.point.getY() + dy[i];

            // Check if coordinates are within bounds
            if (isValidCell(nx, ny, rows, cols)) {
                // Add cell to neighbors arraylist if valid
                this.neighbors.add(new Point(nx, ny));
            }
        }
    }

    // Counts the number of live neighbors for the cell.
    public int getNoOfLiveNeighbors(ArrayList<ArrayList<Cell>> board) {
        int count = 0;
        for (Point p : this.neighbors) {
            if (board.get(p.getX()).get(p.getY()).isState()) {
                count++;
            }
        }
        return count;
    }

    // Updates the cell's state based on the number of live neighbors and Game of Life rules.
    public void evolve(ArrayList<ArrayList<Cell>> board) {
        int noOfLiveNeighbors = getNoOfLiveNeighbors(board);
        // Evolve based on GOL rules
        if (state) {
            if (noOfLiveNeighbors < 2) {
                setState(false);
            } else if (noOfLiveNeighbors > 3) {
                setState(false);
            }
        } else {
            if (noOfLiveNeighbors == 3) {
                setState(true);
            }
        }
    }

    // Checks if a cell with given coordinates is within the grid bounds.
    public static boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    // Overrides toString to provide a string representation of the cell (used for display).
    @Override
    public String toString() {
        return state ? " \u25A0 " : "   ";
    }
}