package org.example.navalbattle.model;

import java.io.Serializable;

/**
 * Represents a position on the naval battle game board.
 */
public class Position implements Serializable {
    private int row;
    private int col;
    private int length;
    private int[][] position = new int[10][10];
    private String errorMessage = "";

    /**
     * Constructs a new position.
     *
     * @param row the row position
     * @param col the column position
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        inicializarTablero();
    }

    /**
     * Initializes the game board with all positions set to 0.
     */
    private void inicializarTablero() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                position[i][j] = 0;
            }
        }
    }

    /**
     * Places a boat on the game board.
     *
     * @param fila the row position
     * @param columna the column position
     * @param isHorizontal whether the boat is placed horizontally
     * @param length the length of the boat
     * @return true if the boat was placed successfully, false otherwise
     */
    public boolean colocarBarco(int fila, int columna, boolean isHorizontal, int length) {
        if (!isValidPosition(fila, columna, isHorizontal, length)) {
            return false;
        }

        if (isHorizontal) {
            for (int i = columna; i < columna + length; i++) {
                position[fila][i] = length;
            }
        } else {
            for (int i = fila; i < fila + length; i++) {
                position[i][columna] = length;
            }
        }
        return true;
    }

    /**
     * Checks if a boat can be placed at the specified position.
     *
     * @param fila the row position
     * @param columna the column position
     * @param isHorizontal whether the boat is placed horizontally
     * @param boatSize the size of the boat
     * @return true if the boat can be placed, false otherwise
     */
    public boolean isValidPosition(int fila, int columna, boolean isHorizontal, int boatSize) {
        if (isHorizontal) {
            if (columna + boatSize > position.length) {
                errorMessage = "El tamaño excede el tablero";
                return false;
            }
            for (int i = columna; i < columna + boatSize; i++) {
                if (position[fila][i] != 0) {
                    errorMessage = "La posición ya está ocupada";
                    return false;
                }
            }
        } else {
            if (fila + boatSize > position.length) {
                errorMessage = "El tamaño excede el tablero";
                return false;
            }
            for (int i = fila; i < fila + boatSize; i++) {
                if (position[i][columna] != 0) {
                    errorMessage = "La posición ya está ocupada";
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the error message if a boat cannot be placed.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the current state of the game board.
     *
     * @return the game board
     */
    public int[][] getMatriz() {
        return position;
    }

    /**
     * Sets the state of a specific position on the game board.
     *
     * @param fila the row position
     * @param columna the column position
     * @param estado the state to set
     * @throws IllegalArgumentException if the row or column indices are not valid
     */
    public void setEstadoCasilla(int fila, int columna, int estado) {
        if (fila >= 0 && fila < position.length && columna >= 0 && columna < position[0].length) {
            position[fila][columna] = estado;
        } else {
            throw new IllegalArgumentException("Índices de fila y columna no válidos");
        }
    }
}