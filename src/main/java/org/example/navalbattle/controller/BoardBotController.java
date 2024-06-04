package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.model.Position;
import org.example.navalbattle.model.Boats;

import java.util.Random;

public class BoardBotController{

    @FXML
    private GridPane positionGridPaneEnemy;
    private Position position;
    @FXML
    public void initialize() {
        // Inicializar el tablero enemigo al cargar el controlador
        initializeEnemyBoard(positionGridPaneEnemy);
    }
    public BoardBotController() {
        this.position = new Position(10, 10);
        // Inicializar la estructura de datos de ubicaciones de barcos enemigos
    }
    public void initializeEnemyBoard(GridPane enemyGridPane) {
        // Seleccionar una configuración al azar
        int config = new Random().nextInt(3); // 0, 1, o 2
        switch (config) {
            case 0:
                setConfiguration1(enemyGridPane);
                break;
            case 1:
                setConfiguration2(enemyGridPane);
                break;
            case 2:
                setConfiguration3(enemyGridPane);
                break;
        }
    }

    private void setConfiguration1(GridPane gridPane) {
        // Configuración 1
        position.colocarBarco(0, 0, true, 4); // Portaaviones
        new Boats(0, 0, 4, true).addToGrid(gridPane);

        position.colocarBarco(2, 0, true, 3); // Submarino 1
        new Boats(2, 0, 3, true).addToGrid(gridPane);

        position.colocarBarco(4, 0, true, 3); // Submarino 2
        new Boats(4, 0, 3, true).addToGrid(gridPane);

        position.colocarBarco(6, 0, true, 2); // Destructor 1
        new Boats(6, 0, 2, true).addToGrid(gridPane);

        position.colocarBarco(8, 0, true, 2); // Destructor 2
        new Boats(8, 0, 2, true).addToGrid(gridPane);

        position.colocarBarco(0, 5, true, 2); // Destructor 3
        new Boats(0, 5, 2, true).addToGrid(gridPane);

        position.colocarBarco(2, 5, true, 1); // Fragata 1
        new Boats(2, 5, 1, true).addToGrid(gridPane);

        position.colocarBarco(4, 5, true, 1); // Fragata 2
        new Boats(4, 5, 1, true).addToGrid(gridPane);

        position.colocarBarco(6, 5, true, 1); // Fragata 3
        new Boats(6, 5, 1, true).addToGrid(gridPane);

        position.colocarBarco(8, 5, true, 1); // Fragata 4
        new Boats(8, 5, 1, true).addToGrid(gridPane);
    }

    private void setConfiguration2(GridPane gridPane) {
        // Configuración 2
        position.colocarBarco(1, 1, false, 4); // Portaaviones
        new Boats(1, 1, 4, false).addToGrid(gridPane);

        position.colocarBarco(1, 6, false, 3); // Submarino 1
        new Boats(1, 6, 3, false).addToGrid(gridPane);

        position.colocarBarco(4, 1, false, 3); // Submarino 2
        new Boats(4, 1, 3, false).addToGrid(gridPane);

        position.colocarBarco(4, 6, false, 2); // Destructor 1
        new Boats(4, 6, 2, false).addToGrid(gridPane);

        position.colocarBarco(6, 1, false, 2); // Destructor 2
        new Boats(6, 1, 2, false).addToGrid(gridPane);

        position.colocarBarco(6, 6, false, 2); // Destructor 3
        new Boats(6, 6, 2, false).addToGrid(gridPane);

        position.colocarBarco(8, 1, false, 1); // Fragata 1
        new Boats(8, 1, 1, false).addToGrid(gridPane);

        position.colocarBarco(8, 3, false, 1); // Fragata 2
        new Boats(8, 3, 1, false).addToGrid(gridPane);

        position.colocarBarco(8, 5, false, 1); // Fragata 3
        new Boats(8, 5, 1, false).addToGrid(gridPane);

        position.colocarBarco(8, 7, false, 1); // Fragata 4
        new Boats(8, 7, 1, false).addToGrid(gridPane);
    }

    private void setConfiguration3(GridPane gridPane) {
        // Configuración 3
        position.colocarBarco(0, 1, true, 4); // Portaaviones
        new Boats(0, 1, 4, true).addToGrid(gridPane);

        position.colocarBarco(2, 3, true, 3); // Submarino 1
        new Boats(2, 3, 3, true).addToGrid(gridPane);

        position.colocarBarco(4, 3, true, 3); // Submarino 2
        new Boats(4, 3, 3, true).addToGrid(gridPane);

        position.colocarBarco(6, 3, true, 2); // Destructor 1
        new Boats(6, 3, 2, true).addToGrid(gridPane);

        position.colocarBarco(8, 3, true, 2); // Destructor 2
        new Boats(8, 3, 2, true).addToGrid(gridPane);

        position.colocarBarco(1, 7, true, 2); // Destructor 3
        new Boats(1, 7, 2, true).addToGrid(gridPane);

        position.colocarBarco(3, 7, true, 1); // Fragata 1
        new Boats(3, 7, 1, true).addToGrid(gridPane);

        position.colocarBarco(5, 7, true, 1); // Fragata 2
        new Boats(5, 7, 1, true).addToGrid(gridPane);

        position.colocarBarco(7, 7, true, 1); // Fragata 3
        new Boats(7, 7, 1, true).addToGrid(gridPane);

        position.colocarBarco(9, 7, true, 1); // Fragata 4
        new Boats(9, 7, 1, true).addToGrid(gridPane);
    }
}
