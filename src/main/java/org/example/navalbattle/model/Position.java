package org.example.navalbattle.model;


public class Position {
    private int row;
    private int col;
    private int length;
    private int[][] position;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        this.position = new int[10][10];
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
    public void colocarBarco(int fila, int columna, boolean isHorizontal,int length) {
        if(isHorizontal){
            int positionFinal=columna+length;
            for (int i = columna; i < positionFinal; i++) {
                position[fila][columna] = 1;
                columna+=1;
            }
        } else if (!isHorizontal) {
            int positionFinal=fila+length;
            for (int i = columna; i < positionFinal; i++) {
                position[fila][columna] = 1;
                fila+=1;
            }
        }
        // 1 representa la presencia de un barco, por ejemplo
    }
    public int obtenerEstadoCasilla(int fila, int columna) {
        // Obtener el estado de una casilla en el tablero (agua o barco)
        return position[fila][columna];
    }
}
