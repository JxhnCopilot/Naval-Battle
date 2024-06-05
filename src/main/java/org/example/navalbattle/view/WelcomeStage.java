package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
    public WelcomeStage() throws IOException {
        //Importamos la Vista de bienvenida
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/navalbattle/welcome-view.fxml"));
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
    public static WelcomeStage getInstance() throws IOException {
        return  WelcomeStage.WelcomeStageHolder.INSTANCE != null ?
                WelcomeStage.WelcomeStageHolder.INSTANCE :
                (WelcomeStage.WelcomeStageHolder.INSTANCE = new WelcomeStage());
    }
    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
    private static class WelcomeStageHolder{
        private static WelcomeStage INSTANCE;
    }
}
