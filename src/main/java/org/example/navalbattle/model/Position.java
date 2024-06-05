package org.example.navalbattle.model;
public class Position {
    private int row;
    private int col;
    private int length;
    private int[][] position = new int[10][10];
    private String errorMessage = "";

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                position[i][j] = 0;
            }
        }
    }

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

    public String getErrorMessage() {
        return errorMessage;
    }


    public int[][] getMatriz() {
        return position;
    }

    public void setEstadoCasilla(int fila, int columna, int estado) {
        if (fila >= 0 && fila < position.length && columna >= 0 && columna < position[0].length) {
            position[fila][columna] = estado;
        } else {
            throw new IllegalArgumentException("Índices de fila y columna no válidos");
        }
    }
}

