package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.IOException;

/**
 * This class is responsible for controlling the welcome screen of the game.
 */
public class WelcomeController {

    /**
     * Handles the play button click event.
     * It deletes the current instance of the welcome stage and creates a new instance of the game stage.
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     */
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance();
    }
}