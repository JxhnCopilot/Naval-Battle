package org.example.navalbattle.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents the enemy's board in the naval battle game.
 */
public class EnemyBoard implements Serializable{
    private static final int BOARD_SIZE = 10;
    private static final int WATER = 0;
    public static final int PORTAVIONES = 4;
    public static final int SUBMARINO = 3;
    public static final int DESTRUCTOR = 2;
    public static final int FRAGATA = 1;
    private static int[][] board;
    private Random random;

    /**
     * Constructs a new enemy board and places the ships.
     */
    public EnemyBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        random = new Random();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = WATER;
            }
        }
        placeShips();
    }
    /**
     * Places all the ships on the board.
     */
    private void placeShips() {
        placeShip(PORTAVIONES, 1);
        placeShip(SUBMARINO, 2);
        placeShip(DESTRUCTOR, 3);
        placeShip(FRAGATA, 4);
    }

    /**
     * Places a specific ship on the board.
     *
     * @param shipSize the size of the ship
     * @param shipCount the number of ships to place
     */
    private void placeShip(int shipSize, int shipCount) {
        for (int i = 0; i < shipCount; i++) {
            int row, col;
            boolean isHorizontal;
            do {
                row = random.nextInt(BOARD_SIZE);
                col = random.nextInt(BOARD_SIZE);
                isHorizontal = random.nextBoolean();
            } while (!canPlaceShip(row, col, isHorizontal, shipSize));
            for (int j = 0; j < shipSize; j++) {
                if (isHorizontal) {
                    board[row][col + j] = shipSize;
                } else {
                    board[row + j][col] = shipSize;
                }
            }
        }
    }

    /**
     * Checks if a ship can be placed at the specified position.
     *
     * @param row the row position
     * @param col the column position
     * @param isHorizontal whether the ship is placed horizontally
     * @param shipSize the size of the ship
     * @return true if the ship can be placed, false otherwise
     */

    private boolean canPlaceShip(int row, int col, boolean isHorizontal, int shipSize) {
        if (isHorizontal) {
            if (col + shipSize > BOARD_SIZE) {
                return false;
            }
            for (int i = col; i < col + shipSize; i++) {
                if (board[row][i] != WATER) {
                    return false;
                }
            }
        } else {
            if (row + shipSize > BOARD_SIZE) {
                return false;
            }
            for (int i = row; i < row + shipSize; i++) {
                if (board[i][col] != WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the current state of the board.
     *
     * @return the board
     */
    public static int[][] getBoard() {
        return board;
    }
}