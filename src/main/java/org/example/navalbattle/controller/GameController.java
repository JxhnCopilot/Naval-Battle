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
import org.example.navalbattle.model.Boats;
import org.example.navalbattle.model.EnemyBoard;
import org.example.navalbattle.model.Position;
import org.example.navalbattle.view.Alerts.AlertBox;
import org.example.navalbattle.view.BoardBotStage;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The GameController class is responsible for managing the game logic.
 * It handles user interactions and updates the game state accordingly.
 */
public class GameController {
    @FXML
    private GridPane positionGridPane, shootGridpane;
    @FXML
    private Button buttonLoad,buttonSaved,startButton, buttonPortaAviones, buttonSubmarinos, buttonDestructores, buttonOrientation, buttonFragatas, buttonEnemyBoard;
    @FXML
    private TextField rowTextField, colTextField;
    @FXML
    private Label labelPortaAviones, labelSubmarinos, labelDestructores, labelFragatas, labelRow, labelCol;
    @FXML
    private ImageView imageViewShootBoard;
    Position position = new Position(10, 10);
    EnemyBoard enemyBoard = new EnemyBoard();
    int fragatas = 4, destructores = 6, submarinos = 6, portaAviones = 4;

    /**
     * This method is called after all @FXML annotated members have been injected.
     * It adds an event to the positionGridPane.
     */
    @FXML
    public void initialize() {
        addEvent(positionGridPane);
    }

    /**
     * Handles the start button click event.
     * It makes the shootGridpane visible and hides other UI elements.
     *
     * @param event the action event
     */
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
        buttonLoad.setVisible(false);
        buttonSaved.setVisible(false);
        addEvent(shootGridpane);
    }

    /**
     * Handles the enemy board button click event.
     * It shows the enemy board stage.
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     */
    public void onHandleButtonEnemyBoard(ActionEvent event) throws IOException {
        BoardBotStage.getInstance().show();
    }

    private Position shootPosition = new Position(10, 10);

    /**
     * Handles the mouse click event on the game board.
     * It updates the game state based on the clicked cell.
     *
     * @param event the mouse event
     * @param row   the row of the clicked cell
     * @param col   the column of the clicked cell
     */
    public void handleMouseClick(MouseEvent event, int row, int col) {
        shootPosition.getMatriz()[row][col] = 1;
        ((ImageView) event.getSource()).setOnMouseClicked(null);
        System.out.println("Diste click");
        int[][] enemyBoard = this.enemyBoard.getBoard();
        if (enemyBoard[row][col] == 1) {
            fragatas--;
            putImageExplotion(row, col, shootGridpane);
        } else if (enemyBoard[row][col] == 2) {
            destructores--;
            putImageExplotion(row, col, shootGridpane);
        } else if (enemyBoard[row][col] == 3) {
            submarinos--;
            putImageExplotion(row, col, shootGridpane);
        } else if (enemyBoard[row][col] == 4) {
            portaAviones--;
            putImageExplotion(row, col, shootGridpane);
        } else {
            putImageFail(row, col, shootGridpane);
        }
        computerPlay();
        winnerVerification();
    }

    /**
     * Gets the number of rows from the rowTextField.
     *
     * @return the number of rows
     */
    public int getRows() {
        return Integer.parseInt(rowTextField.getText());
    }

    /**
     * Gets the number of columns from the colTextField.
     *
     * @return the number of columns
     */
    public int getCols() {
        return Integer.parseInt(colTextField.getText());
    }

    boolean isHorizontal = false;

    /**
     * Handles the orientation button click event.
     * It toggles the orientation of the boat to be placed.
     */
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

    /**
     * Adds a boat to the game board.
     *
     * @param boatSize the size of the boat
     * @param button   the button that triggered the event
     */
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

    /**
     * Handles the portaAviones button click event.
     * It tries to add a portaAviones to the game board.
     *
     * @param event the action event
     */
    public void onHandleButtonPortaAviones(Event event) {
        try {
            addBoat(PORTAAVIONES_SIZE, "portaAviones");
        } catch (Exception e) {
            System.out.println("TextField Vacio");
        }
    }

    /**
     * Handles the submarinos button click event.
     * It tries to add a submarinos to the game board.
     *
     * @param event the action event
     */
    public void onHandleButtonSubmarinos(Event event) {
        try {
            addBoat(SUBMARINOS_SIZE, "submarinos");
        } catch (Exception e) {
            System.out.println("TextField Vacio");
        }
    }

    /**
     * Handles the destructores button click event.
     * It tries to add a destructores to the game board.
     *
     * @param event the action event
     */
    public void onHandleButtonDestrutores(Event event) {

        try {
            addBoat(DESTRUCTORES_SIZE, "destructores");
        } catch (Exception e) {
            System.out.println("TextField Vacio");
        }
    }

    /**
     * Handles the fragatas button click event.
     * It tries to add a fragatas to the game board.
     *
     * @param event the action event
     */
    public void onHandleButtonFragatas(Event event) {
        try {
            addBoat(FRAGATAS_SIZE, "fragatas");
        } catch (Exception e) {
            System.out.println("TextField Vacio");
        }
    }

    /**
     * Adds an event to the given gridPane.
     * It populates the gridPane with cells and assigns a mouse click event to each cell.
     *
     * @param gridPane the gridPane to add the event to
     */
    public void addEvent(GridPane gridPane) {
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/water.png");
        for (int row = 0; row <= 9; row++) {
            for (int col = 0; col <= 9; col++) {
                if (gridPane == shootGridpane) {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    imageView.setImage(image);
                    imageView.setStyle("-fx-cursor: hand");
                    final int r = row;
                    final int c = col;
                    imageView.setOnMouseClicked(event -> handleMouseClick(event, r, c));
                    gridPane.add(imageView, col, row);
                } else {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    imageView.setImage(image);
                    gridPane.add(imageView, col, row);
                }
            }
        }
    }

    /**
     * Verifies if there is a winner.
     * It checks if all boats of a player have been destroyed and declares the other player as the winner.
     */
    public void winnerVerification() {
        try {
            if (fragatas == 0 && destructores == 0 && submarinos == 0 && portaAviones == 0) {
                new AlertBox().showMessage("Ganaste", null, "Has ganado la batalla.");
                GameStage.deleteInstance();
                BoardBotStage.deleteInstance();
                WelcomeStage.getInstance();
            } else if (enemyFragata == 0 && enemyDestructor == 0 && enemySubmarino == 0 && enemyPortaAvion == 0) {
                new AlertBox().showMessage("Perdiste", null, "Has sido derrotado.");
                GameStage.deleteInstance();
                BoardBotStage.deleteInstance();
                WelcomeStage.getInstance();
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    int enemyFragata = 4, enemyDestructor = 6, enemySubmarino = 6, enemyPortaAvion = 4;
    Position enemyPosition = new Position(10, 10);

    /**
     * Simulates the computer's play.
     * It randomly selects a cell to shoot and updates the game state accordingly.
     */
    /**
     * Simulates the computer's play.
     * It randomly selects a cell to shoot and updates the game state accordingly.
     */
    public void computerPlay() {
        int row, col;
        do {
            row = (int) (Math.random() * MAX_BOARD_SIZE);
            col = (int) (Math.random() * MAX_BOARD_SIZE);
        } while (enemyPosition.getMatriz()[row][col] == 1);

        enemyPosition.getMatriz()[row][col] = 1;
        if (position.getMatriz()[row][col] == 1) {
            enemyFragata--;
            putImageExplotion(row, col, positionGridPane);
        } else if (position.getMatriz()[row][col] == 2) {
            enemyDestructor--;
            putImageExplotion(row, col, positionGridPane);
        } else if (position.getMatriz()[row][col] == 3) {
            enemySubmarino--;
            putImageExplotion(row, col, positionGridPane);
        } else if (position.getMatriz()[row][col] == 4) {
            enemyPortaAvion--;
            putImageExplotion(row, col, positionGridPane);
        } else {
            putImageFail(row, col, positionGridPane);
        }
        winnerVerification();
    }

    /**
     * Places an explosion image at the specified grid cell.
     *
     * @param row      the row of the cell
     * @param col      the column of the cell
     * @param gridPane the gridPane to add the image to
     */
    public void putImageExplotion(int row, int col, GridPane gridPane) {
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/explotionIcon.gif");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setImage(image);
        gridPane.add(imageView, col, row);
    }

    /**
     * Places a fail image at the specified grid cell.
     *
     * @param row      the row of the cell
     * @param col      the column of the cell
     * @param gridPane the gridPane to add the image to
     */
    public void putImageFail(int row, int col, GridPane gridPane) {
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/xIcon.png");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setImage(image);
        gridPane.add(imageView, col, row);
    }

    /**
     * Saves the current game state to a file.
     *
     * @param filename the name of the file to save to
     */
    public void saveGameState(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(position);
            out.writeObject(enemyBoard);
            out.writeObject(fragatas);
            out.writeObject(destructores);
            out.writeObject(submarinos);
            out.writeObject(portaAviones);
            out.writeObject(portavionesCount);
            out.writeObject(submarinosCount);
            out.writeObject(destructoresCount);
            out.writeObject(fragatasCount);
            out.writeObject(enemyPortaAvion);
            out.writeObject(enemySubmarino);
            out.writeObject(enemyDestructor);
            out.writeObject(enemyFragata);
            // Serializa cualquier otro estado necesario
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Loads a game state from a file.
     *
     * @param filename the name of the file to load from
     */
    public void loadGameState(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            position = (Position) in.readObject();
            enemyBoard = (EnemyBoard) in.readObject();
            fragatas = (int) in.readObject();
            destructores = (int) in.readObject();
            submarinos = (int) in.readObject();
            portaAviones = (int) in.readObject();
            portavionesCount = (int) in.readObject();
            submarinosCount = (int) in.readObject();
            destructoresCount = (int) in.readObject();
            fragatasCount = (int) in.readObject();
            enemyPortaAvion = (int) in.readObject();
            enemySubmarino = (int) in.readObject();
            enemyDestructor = (int) in.readObject();
            enemyFragata = (int) in.readObject();
            // Deserializa cualquier otro estado necesario
            updateUI(); // Método para actualizar la UI con el estado deserializado
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * Updates the UI based on the current game state.
     */
    public void updateUI() {
        refreshGridPane(positionGridPane, position.getMatriz());

        refreshGridPane(shootGridpane, shootPosition.getMatriz());

        labelPortaAviones.setText("Portaaviones: " + portavionesCount);
        labelSubmarinos.setText("Submarinos: " + submarinosCount);
        labelDestructores.setText("Destructores: " + destructoresCount);
        labelFragatas.setText("Fragatas: " + fragatasCount);

        buttonPortaAviones.setDisable(portavionesCount == 0);
        buttonSubmarinos.setDisable(submarinosCount == 0);
        buttonDestructores.setDisable(destructoresCount == 0);
        buttonFragatas.setDisable(fragatasCount == 0);
        startButton.setDisable(!(portavionesCount == 0 && submarinosCount == 0 && destructoresCount == 0 && fragatasCount == 0));
    }

    /**
     * Refreshes a gridPane based on a given matrix.
     *
     * @param gridPane the gridPane to refresh
     * @param matriz   the matrix to base the refresh on
     */
    private void refreshGridPane(GridPane gridPane, int[][] matriz) {
        gridPane.getChildren().clear();
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz[row].length; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                switch (matriz[row][col]) {
                    case 1 ->
                            imageView.setImage(new Image("file:src/main/resources/org/example/navalbattle/images/fragata.png"));
                    case 2 ->
                            imageView.setImage(new Image("file:src/main/resources/org/example/navalbattle/images/destructor.png"));
                    case 3 ->
                            imageView.setImage(new Image("file:src/main/resources/org/example/navalbattle/images/submarino.png"));
                    case 4 ->
                            imageView.setImage(new Image("file:src/main/resources/org/example/navalbattle/images/portaaviones.png"));
                    default ->
                            imageView.setImage(new Image("file:src/main/resources/org/example/navalbattle/images/water.png"));
                }
                gridPane.add(imageView, col, row);
            }
        }
    }

    /**
     * Handles the save button click event.
     * It saves the current game state to a file.
     *
     * @param event the action event
     */
    public void onHandleButtonSaved(ActionEvent event) {
        saveGameState("gameState.ser");
    }

    /**
     * Handles the load button click event.
     * It loads a game state from a file.
     *
     * @param event the action event
     */
    public void onHandleButtonLoad(ActionEvent event) {
        loadGameState("gameState.ser");
    }
}