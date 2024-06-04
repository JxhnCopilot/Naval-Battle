package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.navalbattle.model.Boats;
import org.example.navalbattle.model.CreatedBoard;
import org.example.navalbattle.model.Position;
import org.example.navalbattle.view.Alerts.AlertBox;
import org.example.navalbattle.view.BoardBotStage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameController {
    @FXML
    private GridPane positionGridPane,shootGridpane;
    @FXML
    private Button startButton, buttonPortaAviones,buttonSubmarinos,buttonDestructores,buttonOrientation,buttonFragatas,buttonEnemyBoard;
    @FXML
    private TextField rowTextField,colTextField;
    @FXML
    private Label labelPortaAviones, labelSubmarinos,labelDestructores,labelFragatas,labelRow,labelCol;
    Position position = new Position(10,10);
    public void onHandleButtonStart(ActionEvent event){
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
        new CreatedBoard().addEvent(shootGridpane);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.println(i+","+j);
                System.out.println(position.obtenerEstadoCasilla(i,j));
            }
        }
    }
    public void onHandleButtonEnemyBoard(ActionEvent event) throws IOException {
        BoardBotStage.getInstance();
        BoardBotStage.deleteInstance();
    }
    public void handleMouseClick(MouseEvent event, int row, int col) {
        System.out.println("Mouse clicked at row: " + row + " col: " + col);
    }
    public int getRows(){
        return Integer.parseInt(rowTextField.getText());
    }
    public int getCols(){
        return Integer.parseInt(colTextField.getText());
    }
    boolean isHorizontal = false;
    public void onHandleButtonOrientation(){
        isHorizontal = !isHorizontal;
        if(!isHorizontal){
            buttonOrientation.setText("Vertical");
        }else{
            buttonOrientation.setText("Horizontal");
        }
    }
    private static final int MAX_BOARD_SIZE = 10;
    private static final int PORTAAVIONES_SIZE = 4;
    private static final int SUBMARINOS_SIZE = 3;
    private static final int DESTRUCTORES_SIZE = 2;
    private static final int FRAGATAS_SIZE = 1;
    private int portavionesCount=1;
    private int submarinosCount=2;
    private int destructoresCount=3;
    private int fragatasCount=4;
    public void addBoat(int boatSize,String button){
        if(isValidPosition(boatSize)){
            if (position.colocarBarco(getRows(), getCols(), isHorizontal, boatSize)) {
                position.colocarBarco(getRows(), getCols(), isHorizontal, boatSize);
                int positionFinal= getCols() + boatSize;
                new Boats(getRows(), getCols(), boatSize, isHorizontal).addToGrid(positionGridPane);
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
            }else {
                String tittle = "Error";
                String header = "La posición ya está ocupada";
                String content = "Por favor ingrese una posición que no esté ocupada por otro barco";
                new AlertBox().showMessage(tittle, header, content);
            }
        } else {
            String tittle = "Error";
            String header = "El tamaño excede el tablero";
            String content = "Por favor ingrese una posicion que no salga del tablero";
            new AlertBox().showMessage(tittle, header, content);
        }
    }
    public boolean isValidPosition(int boatSize){
        if(isHorizontal){
            return getCols() < MAX_BOARD_SIZE - boatSize + 1;
        } else {
            return getRows() < MAX_BOARD_SIZE - boatSize + 1;
        }
    }
    public void onHandleButtonPortaAviones(Event event) {
        addBoat(PORTAAVIONES_SIZE,"portaAviones");
    }
    public void onHandleButtonSubmarinos(Event event) {
        addBoat(SUBMARINOS_SIZE,"submarinos");
    }
    public void onHandleButtonDestrutores(Event event) {
        addBoat(DESTRUCTORES_SIZE,"destructores");
    }
    public void onHandleButtonFragatas(Event event) {
        addBoat(FRAGATAS_SIZE,"fragatas");
    }
}