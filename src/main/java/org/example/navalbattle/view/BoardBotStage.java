package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the bot's board stage in the naval battle game.
 */
public class BoardBotStage extends Stage {
    /**
     * Constructs a new bot's board stage.
     *
     * @throws IOException if an I/O error occurs
     */
    public BoardBotStage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/navalbattle/board-bot-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Tablero Bot");
        getIcons().add(new Image("file:src/main/resources/org/example/navalbattle/images/favicon.png"));
        setResizable(false);
        show();
    }

    /**
     * Returns the instance of the bot's board stage.
     *
     * @return the instance of the bot's board stage
     * @throws IOException if an I/O error occurs
     */
    public static BoardBotStage getInstance() throws IOException {
        return  BoardBotStage.BoardBotStageHolder.INSTANCE != null ?
                BoardBotStage.BoardBotStageHolder.INSTANCE :
                (BoardBotStage.BoardBotStageHolder.INSTANCE = new BoardBotStage());
    }

    /**
     * Deletes the instance of the bot's board stage.
     */
    public static void deleteInstance() {
        BoardBotStage.BoardBotStageHolder.INSTANCE.close();
        BoardBotStage.BoardBotStageHolder.INSTANCE = null;
    }

    /**
     * Holds the instance of the bot's board stage.
     */
    private static class BoardBotStageHolder{
        private static BoardBotStage INSTANCE;
    }
}