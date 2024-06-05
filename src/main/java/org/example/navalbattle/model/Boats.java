package org.example.navalbattle.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a boat in the naval battle game.
 */
public class Boats {
    private int row;
    private int col;
    private int length;
    private boolean horizontal;
    private Rectangle[] squares;

    /**
     * Constructs a new boat.
     *
     * @param row the row position of the boat
     * @param col the column position of the boat
     * @param length the length of the boat
     * @param horizontal whether the boat is placed horizontally
     */
    public Boats(int row, int col, int length, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.length = length;
        this.horizontal = horizontal;
        this.squares = new Rectangle[length];
        createBoat();
    }

    /**
     * Creates the boat by initializing its squares based on the length of the boat.
     * Different colors are used for different lengths.
     */
    private void createBoat() {
        if (length == 4) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.GRAY);
                squares[i].setStroke(Color.BLACK);
            }
        } else if (length == 3) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.RED);
                squares[i].setStroke(Color.BLACK);
            }
        } else if (length == 2) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.GREEN);
                squares[i].setStroke(Color.BLACK);
            }
        } else if (length == 1) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.WHITE);
                squares[i].setStroke(Color.BLACK);
            }
        }
    }

    /**
     * Adds the boat to the specified grid pane.
     *
     * @param gridPane the grid pane to add the boat to
     */
    public void addToGrid(GridPane gridPane) {
        for (int i = 0; i < length; i++) {
            if (horizontal) {
                gridPane.add(squares[i], col + i, row);
            } else {
                gridPane.add(squares[i], col, row + i);
            }
        }
    }

    /**
     * Returns the row position of the boat.
     *
     * @return the row position of the boat
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column position of the boat.
     *
     * @return the column position of the boat
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns the length of the boat.
     *
     * @return the length of the boat
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the boat is placed horizontally.
     *
     * @return true if the boat is placed horizontally, false otherwise
     */
    public boolean isHorizontal() {
        return horizontal;
    }
}