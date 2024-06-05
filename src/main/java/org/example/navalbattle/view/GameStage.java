package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the game stage in the naval battle game.
 */
public class GameStage extends Stage {
    /**
     * Constructs a new game stage.
     *
     * @throws IOException if an I/O error occurs
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/navalbattle/game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Naval Battle");
        getIcons().add(new Image("file:src/main/resources/org/example/navalbattle/images/favicon.png"));
        setResizable(false);
        show();
    }

    /**
     * Returns the instance of the game stage.
     *
     * @return the instance of the game stage
     * @throws IOException if an I/O error occurs
     */
    public static GameStage getInstance() throws IOException {
        return  GameStage.GameStageHolder.INSTANCE != null ?
                GameStage.GameStageHolder.INSTANCE :
                (GameStage.GameStageHolder.INSTANCE = new GameStage());
    }

    /**
     * Deletes the instance of the game stage.
     */
    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }

    /**
     * Holds the instance of the game stage.
     */
    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }
}