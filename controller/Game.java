// Game.java
package controller;
import model.Cell;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// Represents the overall Game of Life simulation.
public class Game {
    private int rows;
    private int cols;
    int simCount = 0;
    private ArrayList<ArrayList<Cell>> board;
    private int noOfGenerations;

    // Constructor to initialize the game with the specified number of rows and columns.
    public Game(int rows, int cols,String ptN) throws FileNotFoundException {
        this.rows = rows;
        this.cols = cols;
        initializeBoard(ptN);
        noOfGenerations = 0;
    }

    // Initializes the game board with cells having random initial states.
    public void initializeBoard(String pattenFileName) throws FileNotFoundException {
        //read file and convert it to data for game board initiation
        int fileData;
        FileReader reader = new FileReader(pattenFileName);
        this.board = new ArrayList<>(this.rows);
        for (int i = 0; i < this.rows; i++) {
            this.board.add(new ArrayList<>(this.cols));
            for (int j = 0; j < this.cols; j++) {
                //data from file is read
                try {
                    fileData = reader.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //for each point there will be a cell for every part of the file that isn't a space
                //line breaks are categorized by a 13 followed by a 10, the 13 can be ignored but the 10 increases the j-value, so it needs to decrease j by 1 when its ignored
                boolean bData = false;
                if (fileData != 10){
                    if (fileData != 13) {
                        if (fileData == 42) {
                            bData = true;
                        }
                    } else {
                        while (j < this.cols - 1) {
                            this.board.get(i).add(new Cell(i, j, this.rows, this.cols, false));
                            j++;
                        }
                    }

                this.board.get(i).add(new Cell(i, j, this.rows, this.cols, bData));
                } else {j--;}
            }
        }
        //close reader
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Sets the state of a cell at specified row and column.
    public void setCellState(int row, int col, boolean state) {
        if (Cell.isValidCell(row, col, this.rows, this.cols)) {
            this.board.get(row).get(col).setState(state);
        }
    }

    // Simulates the next generation of the Game of Life.
    public void simulate(int genNum, int speed) {
        int localSimCount = 0;
        while (localSimCount < genNum) {
            localSimCount++;
            try {
                simCount++;
                System.out.println("Simulating gen " + simCount);
                Thread.sleep(1000 / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        ArrayList<ArrayList<Cell>> newGeneration = createDeepCopy();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newGeneration.get(i).get(j).evolve(this.board);
            }
        }
        noOfGenerations++;
        this.board = newGeneration;
        displayBoard();
    }

}
        // Creates a deep copy of the current generation's board.
        private ArrayList<ArrayList<Cell>> createDeepCopy () {
            ArrayList<ArrayList<Cell>> deepCopy = new ArrayList<>(this.rows);
            for (int i = 0; i < this.rows; i++) {
                deepCopy.add(new ArrayList<>(this.cols));
                for (int j = 0; j < this.cols; j++) {
                    deepCopy.get(i).add(new Cell(this.board.get(i).get(j).getPoint().getX(),
                            this.board.get(i).get(j).getPoint().getY(),
                            this.rows, this.cols, this.board.get(i).get(j).isState()));
                }
            }
            return deepCopy;
        }

        // Displays the current generation's board.
        public void displayBoard () {
            System.out.println("Generation: " + noOfGenerations);
            //prints top number and lines
            System.out.print("    ");
            for (int j = 0; j < this.cols; j++) {
                System.out.print(" " + (j+1) + " ");
            }
            System.out.println("");
            System.out.print("   \u250f");
            for (int j = 0; j < this.cols; j++) {
                System.out.print("\u2501\u2501\u2501");
            }
            System.out.print("\u2513");
            System.out.println("");
            for (int i = 0; i < this.rows; i++) {
                //if middle section is one digit there will be a space, if its 2 digits there won't be a space
                if (i<9) {
                    System.out.print(" "+(i + 1) + " \u2503");
                }
                if (i>=9){
                    System.out.print((i + 1) + " \u2503");
                }
                for (int j = 0; j < this.cols; j++) {

                    System.out.print(this.board.get(i).get(j));
                }
                //prints bottom lines
                System.out.print("\u2503");
                System.out.println();
            }
            System.out.print("   \u2517");
            for (int j = 0; j < this.cols; j++) {
                System.out.print("\u2501\u2501\u2501");
            }
            System.out.print("\u251b");
            System.out.println("");

        }

}