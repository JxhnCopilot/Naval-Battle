package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


import java.io.IOException;

public class GameController {
    @FXML
    private GridPane positionGridPane;
    @FXML
    private GridPane shootGridpane;
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        positionGridPane.setVisible(true);
        shootGridpane.setVisible(true);
    }
}
