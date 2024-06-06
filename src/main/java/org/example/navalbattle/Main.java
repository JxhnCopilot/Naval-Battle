package org.example.navalbattle;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.IOException;

/**
 * The main class that launches the application.
 */
public class  Main extends Application {
    /**
     * The main method that launches the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application by showing the welcome stage.
     *
     * @param primaryStage the primary stage for this application
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}