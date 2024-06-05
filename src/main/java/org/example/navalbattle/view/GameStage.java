package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    public GameStage() throws IOException {
        //Importamos la Vista de bienvenida
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/navalbattle/game-view.fxml"));
        //Creamos el Parent
        Parent root = loader.load();
        //Creamos una nueva Escena
        Scene scene = new Scene(root);
        //Insertamos la Escena al Stage
        setScene(scene);
        //Insertamos un titulo al Stage
        setTitle("Naval Battle");
        //Insertamos un icono al Stage
        getIcons().add(new Image("file:src/main/resources/org/example/navalbattle/images/favicon.png"));
        //Hacemos que el Stage no se pueda redimensionar
        setResizable(false);
        //Agregamos el icono al Stage
        //Hacemos el show al stage
        show();
    }
    //Patron Singleton
    public static GameStage getInstance() throws IOException {
        return  GameStage.GameStageHolder.INSTANCE != null ?
                GameStage.GameStageHolder.INSTANCE :
                (GameStage.GameStageHolder.INSTANCE = new GameStage());
    }
    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }
    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }
}
