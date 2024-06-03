package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.model.Boats;
import org.example.navalbattle.model.CreatedBoard;
import org.example.navalbattle.model.Position;
import org.example.navalbattle.view.Alerts.AlertBox;

public class GameController {
    @FXML
    private GridPane positionGridPane;
    @FXML
    private GridPane shootGridpane;
    @FXML
    private Button startButton;
    @FXML
    private TextField rowTextField;
    @FXML
    private TextField colTextField;
    @FXML
    private Button buttonPortaAviones;
    @FXML
    private Label labelPortaAviones;
    @FXML
    private Button buttonSubmarinos;
    @FXML
    private Label labelSubmarinos;
    @FXML
    private Button buttonDestructores;
    @FXML
    private Label labelDestructores;
    @FXML
    private Button buttonOrientation;
    @FXML
    private Button buttonFragatas;
    @FXML
    private Label labelFragatas;
    @FXML
    public void initialize() {
    }
    public void onHandleButtonPlay(ActionEvent event){
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
        new CreatedBoard().addEvent(shootGridpane);
    }
    public void handleMouseClick(MouseEvent event, int row, int col) {
        System.out.println("Clicked on cell: (" + row + ", " + col + ")");
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
    Position position = new Position(10,10);
    public void onHandleButtonPortaAviones(Event event) {
        if(isHorizontal){
            if(getCols() <7) {
                position.colocarBarco(getRows(), getCols(), isHorizontal, 4);
                new Boats(getRows(), getCols(), 4, isHorizontal).addToGrid(positionGridPane);
                rowTextField.clear();
                colTextField.clear();
                buttonPortaAviones.setDisable(true);
                labelPortaAviones.setText("0");
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        } else if (!isHorizontal) {
            if(getRows()<7) {
                position.colocarBarco(getRows(), getCols(), isHorizontal, 4);
                new Boats(getRows(), getCols(), 4, isHorizontal).addToGrid(positionGridPane);
                rowTextField.clear();
                colTextField.clear();
                buttonPortaAviones.setDisable(true);
                labelPortaAviones.setText("0");
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        }
    }

    private int submarinos=2;
    public void onHandleButtonSubmarinos(Event event) {
        if(isHorizontal){
            if(getCols() <8) {
                position.colocarBarco(getRows(),getCols(),isHorizontal,3);
                new Boats(getRows(),getCols(),3,isHorizontal).addToGrid(positionGridPane);
                submarinos = submarinos-1;
                rowTextField.clear();
                colTextField.clear();
                if(submarinos==0){
                    buttonSubmarinos.setDisable(true);
                }
                labelSubmarinos.setText(String.valueOf(submarinos));
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        } else if (!isHorizontal) {
            if(getRows()<8) {
                position.colocarBarco(getRows(),getCols(),isHorizontal,3);
                new Boats(getRows(),getCols(),3,isHorizontal).addToGrid(positionGridPane);
                submarinos = submarinos-1;
                rowTextField.clear();
                colTextField.clear();
                if(submarinos==0){
                    buttonSubmarinos.setDisable(true);
                }
                labelSubmarinos.setText(String.valueOf(submarinos));
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        }
    }
    private int destructores=3;
    public void onHandleButtonDestrutores(Event event) {
        if(isHorizontal){
            if(getCols() <9) {
                position.colocarBarco(getRows(),getCols(),isHorizontal,2);
                new Boats(getRows(),getCols(),2,isHorizontal).addToGrid(positionGridPane);
                destructores = destructores-1;
                rowTextField.clear();
                colTextField.clear();
                if(destructores==0){
                    buttonDestructores.setDisable(true);
                }
                labelDestructores.setText(String.valueOf(destructores));
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        } else if (!isHorizontal) {
            if(getRows()<9) {
                position.colocarBarco(getRows(),getCols(),isHorizontal,2);
                new Boats(getRows(),getCols(),2,isHorizontal).addToGrid(positionGridPane);
                destructores = destructores-1;
                rowTextField.clear();
                colTextField.clear();
                if(destructores==0){
                    buttonDestructores.setDisable(true);
                }
                labelDestructores.setText(String.valueOf(destructores));
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        }
    }
    private int fragatas=4;
    public void onHandleButtonFragatas(Event event) {
        if(isHorizontal){
            if(getCols() <10) {
                position.colocarBarco(getRows(),getCols(),isHorizontal,1);
                new Boats(getRows(),getCols(),1,isHorizontal).addToGrid(positionGridPane);
                fragatas-=1;
                rowTextField.clear();
                colTextField.clear();
                if(fragatas==0){
                    buttonFragatas.setDisable(true);
                }
                labelFragatas.setText(String.valueOf(fragatas));
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        } else if (!isHorizontal) {
            if(getRows()<10) {
                position.colocarBarco(getRows(),getCols(),isHorizontal,1);
                new Boats(getRows(),getCols(),1,isHorizontal).addToGrid(positionGridPane);
                fragatas -=1;
                rowTextField.clear();
                colTextField.clear();
                if(fragatas==0){
                    buttonFragatas.setDisable(true);
                }
                labelFragatas.setText(String.valueOf(fragatas));
            }else{
                String tittle = "Error";
                String header = "El tamaño excede el tablero";
                String content = "Por favor ingrese una posicion que no salga del tablero";
                new AlertBox().showMessage(tittle, header, content);
            }
        }
    }
}




