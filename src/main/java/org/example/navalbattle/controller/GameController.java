package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class GameController {
    @FXML
    private GridPane positionGridPane;
    @FXML
    private GridPane shootGridpane;
    @FXML
    private GridPane positionGridPane1;
    @FXML
    private Button startButton;
    @FXML
    public void initialize() {
        addEvent();
    }
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        positionGridPane.setVisible(true);
        shootGridpane.setVisible(true);
        positionGridPane1.setVisible(false);
        startButton.setVisible(false);
    }
    private void handleMouseClick(MouseEvent event, int row, int col) {
        System.out.println("Clicked on cell: (" + row + ", " + col + ")");
        // Aquí puedes agregar la lógica adicional que necesitas al hacer clic en una celda.
    }
    public void addEvent() {
        for (int row = 0; row <= 9; row++) {
            for (int col = 0; col <= 9; col++) {
                Pane pane = new Pane();
                final int r = row;
                final int c = col;
                pane.setOnMouseClicked(event -> handleMouseClick(event, r, c));
                pane.setStyle("-fx-background-color: blue;" +
                                "-fx-border-color: black;"+
                        "-fx-border-width: 2px");
                shootGridpane.add(pane, col, row);
            }
        }
    }
}




