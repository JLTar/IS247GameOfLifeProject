// Point.java
package model;

// Represents a point in 2D space with x and y coordinates.
public class Point {
    private int x;
    private int y;

    // Constructor to initialize the point with specified x and y coordinates.
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter method for the x coordinate.
    public int getX() {
        return x;
    }

    // Setter method for the x coordinate.
    public void setX(int x) {
        this.x = x;
    }

    // Getter method for the y coordinate.
    public int getY() {
        return y;
    }

    // Setter method for the y coordinate.
    public void setY(int y) {
        this.y = y;
    }
}