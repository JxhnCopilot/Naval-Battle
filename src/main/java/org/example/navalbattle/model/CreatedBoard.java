package org.example.navalbattle.model;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.navalbattle.controller.GameController;

public class CreatedBoard {
    public void addEvent(GridPane gridPane) {
        for (int row = 0; row <= 9; row++) {
            for (int col = 0; col <= 9; col++) {
                Pane pane= new Pane();
                final int r = row;
                final int c = col;
                pane.setOnMouseClicked(event -> new GameController().handleMouseClick(event, r, c));
                pane.setStyle("-fx-background-color: blue;" +
                        "-fx-border-color: black;"+
                        "-fx-border-width: 2px");
                gridPane.add(pane, col, row);
                }
            }
        }
    }

