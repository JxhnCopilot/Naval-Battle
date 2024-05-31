package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.IOException;

public class WelcomeController {
    public void onHandleButtonPlay(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance();
    }
}
