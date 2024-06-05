package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
public class BoardBotStage extends Stage {
    public BoardBotStage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/navalbattle/board-bot-view.fxml"));
        //Creamos el Parent
        Parent root = loader.load();
        //Creamos una nueva Escena
        Scene scene = new Scene(root);
        //Insertamos la Escena al Stage
        setScene(scene);
        //Insertamos un titulo al Stage
        setTitle("Tablero Bot");
        //Insertamos un icono al Stage
        getIcons().add(new Image("file:src/main/resources/org/example/navalbattle/images/favicon.png"));
        //Hacemos que el Stage no se pueda redimensionar
        setResizable(false);
        //Agregamos el icono al Stage
        //Hacemos el show al stage
        show();
    }
    public static BoardBotStage getInstance() throws IOException {
        return  BoardBotStage.BoardBotStageHolder.INSTANCE != null ?
                BoardBotStage.BoardBotStageHolder.INSTANCE :
                (BoardBotStage.BoardBotStageHolder.INSTANCE = new BoardBotStage());
    }
    public static void deleteInstance() {
        BoardBotStage.BoardBotStageHolder.INSTANCE.close();
        BoardBotStage.BoardBotStageHolder.INSTANCE = null;
    }
    private static class BoardBotStageHolder{
        private static BoardBotStage INSTANCE;
    }
}
