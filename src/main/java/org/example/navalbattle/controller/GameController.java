package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.navalbattle.model.Boats;
import org.example.navalbattle.model.EnemyBoard;
import org.example.navalbattle.model.Position;
import org.example.navalbattle.view.Alerts.AlertBox;
import org.example.navalbattle.view.Alerts.IAlertBox;
import org.example.navalbattle.view.BoardBotStage;
import org.example.navalbattle.view.GameStage;

import java.io.IOException;
import java.util.Objects;

public class GameController {
    @FXML
    private GridPane positionGridPane, shootGridpane;
    @FXML
    private Button startButton, buttonPortaAviones, buttonSubmarinos, buttonDestructores, buttonOrientation, buttonFragatas, buttonEnemyBoard;
    @FXML
    private TextField rowTextField, colTextField;
    @FXML
    private Label labelPortaAviones, labelSubmarinos, labelDestructores, labelFragatas, labelRow, labelCol;
    @FXML
    private ImageView imageViewShootBoard;
    Position position = new Position(10, 10);
    EnemyBoard enemyBoard = new EnemyBoard();
    int fragatas = 4 , destructores = 6, submarinos = 6, portaAviones = 4;


    @FXML
    public void initialize() {
        addEvent(positionGridPane);
    }

    public void onHandleButtonStart(ActionEvent event) {
        shootGridpane.setVisible(true);
        startButton.setVisible(false);
        buttonFragatas.setVisible(false);
        buttonDestructores.setVisible(false);
        buttonSubmarinos.setVisible(false);
        buttonPortaAviones.setVisible(false);
        rowTextField.setVisible(false);
        colTextField.setVisible(false);
        labelDestructores.setVisible(false);
        labelFragatas.setVisible(false);
        labelPortaAviones.setVisible(false);
        labelSubmarinos.setVisible(false);
        buttonOrientation.setVisible(false);
        labelRow.setVisible(false);
        labelCol.setVisible(false);
        imageViewShootBoard.setVisible(true);
        addEvent(shootGridpane);
    }
    public void onHandleButtonEnemyBoard(ActionEvent event) throws IOException {
        BoardBotStage.getInstance().show();
    }

    public void handleMouseClick(MouseEvent event, int row, int col) {
        ((ImageView) event.getSource()).setOnMouseClicked(null);
        System.out.println("Diste click");
        int[][] enemyBoard = this.enemyBoard.getBoard();
        if (enemyBoard[row][col] == 1) {
            fragatas--;
            putImageExplotion(row, col,shootGridpane);
        } else if (enemyBoard[row][col] == 2) {
            destructores--;
            putImageExplotion(row, col,shootGridpane);
        } else if (enemyBoard[row][col] == 3) {
            submarinos--;
            putImageExplotion(row, col,shootGridpane);
        } else if (enemyBoard[row][col] == 4) {
            portaAviones--;
            putImageExplotion(row, col,shootGridpane);
        } else {
            putImageFail(row, col,shootGridpane);
        }
        computerPlay();
        winnerVerification();
    }

    public int getRows() {
        return Integer.parseInt(rowTextField.getText());
    }

    public int getCols() {
        return Integer.parseInt(colTextField.getText());
    }

    boolean isHorizontal = false;

    public void onHandleButtonOrientation() {
        isHorizontal = !isHorizontal;
        if (!isHorizontal) {
            buttonOrientation.setText("Vertical");
        } else {
            buttonOrientation.setText("Horizontal");
        }
    }

    private static final int MAX_BOARD_SIZE = 10;
    private static final int PORTAAVIONES_SIZE = 4;
    private static final int SUBMARINOS_SIZE = 3;
    private static final int DESTRUCTORES_SIZE = 2;
    private static final int FRAGATAS_SIZE = 1;
    private int portavionesCount = 1;
    private int submarinosCount = 2;
    private int destructoresCount = 3;
    private int fragatasCount = 4;

    public void addBoat(int boatSize, String button) {
        int fila = getRows();
        int columna = getCols();

        if (position.isValidPosition(fila, columna, isHorizontal, boatSize)) {
            if (position.colocarBarco(fila, columna, isHorizontal, boatSize)) {
                new Boats(fila, columna, boatSize, isHorizontal).addToGrid(positionGridPane);
                rowTextField.clear();
                colTextField.clear();
                if (Objects.equals(button, "portaAviones")) {
                    portavionesCount -= 1;
                    labelPortaAviones.setText("Portaaviones: " + portavionesCount);
                    if (portavionesCount == 0) {
                        buttonPortaAviones.setDisable(true);
                    }
                } else if (Objects.equals(button, "submarinos")) {
                    submarinosCount -= 1;
                    labelSubmarinos.setText("Submarinos: " + submarinosCount);
                    if (submarinosCount == 0) {
                        buttonSubmarinos.setDisable(true);
                    }
                } else if (Objects.equals(button, "destructores")) {
                    destructoresCount -= 1;
                    labelDestructores.setText("Destructores: " + destructoresCount);
                    if (destructoresCount == 0) {
                        buttonDestructores.setDisable(true);
                    }
                } else if (Objects.equals(button, "fragatas")) {
                    fragatasCount -= 1;
                    labelFragatas.setText("Fragatas: " + fragatasCount);
                    if (fragatasCount == 0) {
                        buttonFragatas.setDisable(true);
                    }
                }
                if (portavionesCount == 0 && submarinosCount == 0 && destructoresCount == 0 && fragatasCount == 0) {
                    startButton.setDisable(false);
                }
            } else {
                String tittle = "Error";
                String header = position.getErrorMessage();
                String content = "Por favor ingrese una posición válida.";
                new AlertBox().showMessage(tittle, header, content);
            }
        } else {
            String tittle = "Error";
            String header = position.getErrorMessage();
            String content = "Por favor ingrese una posición válida.";
            new AlertBox().showMessage(tittle, header, content);
        }
    }

    public void onHandleButtonPortaAviones(Event event) {
        addBoat(PORTAAVIONES_SIZE, "portaAviones");
    }

    public void onHandleButtonSubmarinos(Event event) {
        addBoat(SUBMARINOS_SIZE, "submarinos");
    }

    public void onHandleButtonDestrutores(Event event) {
        addBoat(DESTRUCTORES_SIZE, "destructores");
    }

    public void onHandleButtonFragatas(Event event) {
        addBoat(FRAGATAS_SIZE, "fragatas");
    }

    public void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void addEvent(GridPane gridPane) {
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/water.png");
        for (int row = 0; row <= 9; row++) {
            for (int col = 0; col <= 9; col++) {
                if(gridPane==shootGridpane){
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    imageView.setImage(image);
                    imageView.setStyle("-fx-cursor: hand");
                    final int r = row;
                    final int c = col;
                    imageView.setOnMouseClicked(event -> handleMouseClick(event, r, c));
                    gridPane.add(imageView, col, row);
                }
                else{
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    imageView.setImage(image);
                    gridPane.add(imageView  , col, row);
                }
            }
        }
    }

    public void winnerVerification() {
        try{
        if (fragatas == 0 && destructores == 0 && submarinos == 0 && portaAviones == 0) {
            new AlertBox().showMessage("Ganaste", null, "Has ganado la batalla.");
            GameStage.deleteInstance();
            BoardBotStage.deleteInstance();
        } else if (enemyFragata == 0 && enemyDestructor == 0 && enemySubmarino == 0 && enemyPortaAvion == 0) {
            new AlertBox().showMessage("Perdiste", null, "Has sido derrotado.");
            GameStage.deleteInstance();
            BoardBotStage.deleteInstance();
        }
        }catch (Exception e){
            System.out.println("Error");
        }
    }
    int enemyFragata = 4, enemyDestructor =6, enemySubmarino = 6, enemyPortaAvion = 4;
    public void computerPlay(){
        int row = (int) (Math.random() * MAX_BOARD_SIZE);
        int col = (int) (Math.random() * MAX_BOARD_SIZE);
        if (position.getMatriz()[row][col] == 1) {
            enemyFragata--;
            putImageExplotion(row, col,positionGridPane);
        } else if (position.getMatriz()[row][col]== 2) {
            enemyDestructor--;
            putImageExplotion(row, col,positionGridPane);
        } else if (position.getMatriz()[row][col]== 3) {
            enemySubmarino--;
            putImageExplotion(row, col,positionGridPane);
        } else if (position.getMatriz()[row][col]== 4) {
            enemyPortaAvion--;
            putImageExplotion(row, col,positionGridPane);
        } else {
            putImageFail(row, col,positionGridPane);
        }
        winnerVerification();
    }
    public void putImageExplotion(int row, int col,GridPane gridPane){
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/explotionIcon.gif");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setImage(image);
        gridPane.add(imageView, col, row);
    }
    public void putImageFail(int row, int col,GridPane gridPane){
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/xIcon.png");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setImage(image);
        gridPane.add(imageView, col, row);
    }
}
