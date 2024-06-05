package org.example.navalbattle.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boats {
    private int row;
    private int col;
    private int length;
    private boolean horizontal;
    private Rectangle[] squares;

    public Boats(int row, int col, int length, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.length = length;
        this.horizontal = horizontal;
        this.squares = new Rectangle[length];
        createBoat();
    }

    private void createBoat() {
        if (length== 4) {
        for (int i = 0; i < length; i++) {
            squares[i] = new Rectangle(40, 40);
            squares[i].setFill(Color.GRAY);
            squares[i].setStroke(Color.BLACK);
        }
        }else if (length== 3) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.RED);
                squares[i].setStroke(Color.BLACK);
            }
        }else if (length== 2) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.GREEN);
                squares[i].setStroke(Color.BLACK);
            }
        } else if (length==1) {
            for (int i = 0; i < length; i++) {
                squares[i] = new Rectangle(40, 40);
                squares[i].setFill(Color.WHITE);
                squares[i].setStroke(Color.BLACK);
            }
        }
    }

    public void addToGrid(GridPane gridPane) {
        for (int i = 0; i < length; i++) {
            if (horizontal) {
                gridPane.add(squares[i], col + i, row);
            } else {
                gridPane.add(squares[i], col, row + i);
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getLength() {
        return length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
