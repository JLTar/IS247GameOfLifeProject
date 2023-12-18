// Main.java
package view;

import controller.Game;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Entry point for the Game of Life simulation.
public class Main {
    private static int rows;
    private static int cols;


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Conway's Game of Life Simulation!");
        // asks user for size input
        System.out.print("insert number of rows: ");
        Scanner sizeScanner = new Scanner(System.in);
        rows = sizeScanner.nextInt();
        System.out.print("insert number of columns: ");
        cols = sizeScanner.nextInt();
        //input from config for pattern
        if (args.length < 1) {
            System.out.println("Usage: java GameOfLifeApplication <PatternFileName>");
            return;
        }
        String patternFileName = args[0];
        // Initiate the game
        Game game = new Game(rows, cols,patternFileName);
        // Display the initial state
        game.displayBoard();
        //asks user to set the number of inputs and their speed
        Scanner genScanner = new Scanner(System.in);
        int choice = 1;
        System.out.print("choose the number of generations you want the code to run for: ");
        int idNumber = genScanner.nextInt();
        System.out.print("choose animation speed in frames per second: ");
        int animationSpeed = genScanner.nextInt();

        // Ask user to choose if to simulate next set of generations or exit the program
        while (choice == 1) {

            System.out.print("Enter 1 to simulate next " + idNumber + " generations or 0 to exit: ");
            choice = genScanner.nextInt();
            if (choice == 1) {
                game.simulate(idNumber,animationSpeed);
            }
        }

        System.out.println("Exiting program!");
    }
}

