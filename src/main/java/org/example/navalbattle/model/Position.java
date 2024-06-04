package org.example.navalbattle.model;


public class Position {
    private int row;
    private int col;
    private int length;
    private int[][] position=new int[10][10];
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        inicializarTablero();
    }
    private void inicializarTablero() {
        // Inicializar el tablero con valores predeterminados, por ejemplo, agua
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                position[i][j] = 0; // 0 representa agua, por ejemplo
            }
        }
    }
    public boolean colocarBarco(int fila, int columna, boolean isHorizontal,int length) {
        if(isHorizontal){
            int positionFinal=columna+length;
            for (int i = columna; i < positionFinal; i++) {
                if (position[fila][i] == 1) {
                    return false; // La posición ya está ocupada
                }
            }
            for (int i = columna; i < positionFinal; i++) {
                position[fila][i] = 1;
            }
        } else {
            int positionFinal=fila+length;
            for (int i = fila; i < positionFinal; i++) {
                if (position[i][columna] == 1) {
                    return false; // La posición ya está ocupada
                }
            }
            for (int i = fila; i < positionFinal; i++) {
                position[i][columna] = 1;
            }
        }
        return true; // El barco se colocó con éxito
    }
    public int obtenerEstadoCasilla(int fila, int columna) {
        // Obtener el estado de una casilla en el tablero (agua o barco)
        return position[fila][columna];
    }
    public int[][] getMatriz(){
        return position;
    }
    public void setEstadoCasilla(int fila, int columna, int estado) {
        // Verificar que los índices de fila y columna son válidos
        if (fila >= 0 && fila < position.length && columna >= 0 && columna < position[0].length) {
            // Cambiar el estado de la celda
            position[fila][columna] = estado;
        } else {
            // Lanzar una excepción si los índices de fila y columna no son válidos
            throw new IllegalArgumentException("Índices de fila y columna no válidos");
        }
    }
}
