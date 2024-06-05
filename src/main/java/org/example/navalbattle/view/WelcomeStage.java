package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the welcome stage in the naval battle game.
 */
public class WelcomeStage extends Stage {
    /**
     * Constructs a new welcome stage.
     *
     * @throws IOException if an I/O error occurs
     */
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/navalbattle/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Naval Battle");
        getIcons().add(new Image("file:src/main/resources/org/example/navalbattle/images/favicon.png"));
        setResizable(false);
        show();
    }

    /**
     * Returns the instance of the welcome stage.
     *
     * @return the instance of the welcome stage
     * @throws IOException if an I/O error occurs
     */
    public static WelcomeStage getInstance() throws IOException {
        return  WelcomeStage.WelcomeStageHolder.INSTANCE != null ?
                WelcomeStage.WelcomeStageHolder.INSTANCE :
                (WelcomeStage.WelcomeStageHolder.INSTANCE = new WelcomeStage());
    }

    /**
     * Deletes the instance of the welcome stage.
     */
    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }

    /**
     * Holds the instance of the welcome stage.
     */
    private static class WelcomeStageHolder{
        private static WelcomeStage INSTANCE;
    }
}