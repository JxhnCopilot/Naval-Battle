package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.navalbattle.model.EnemyBoard;

public class BoardBotController{
    @FXML
    private GridPane positionGridPaneEnemy;
    @FXML
    public void initialize() {
        int[][] board = new int[10][10];
        addEvent(positionGridPaneEnemy);
        drawEnemyBoard();
    }

    private void drawEnemyBoard() {
        int[][] board = EnemyBoard.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Color color;
                switch (board[row][col]) {
                    case EnemyBoard.PORTAVIONES:
                        color = Color.BLUE;
                        break;
                    case EnemyBoard.SUBMARINO:
                        color = Color.GREEN;
                        break;
                    case EnemyBoard.DESTRUCTOR:
                        color = Color.YELLOW;
                        break;
                    case EnemyBoard.FRAGATA:
                        color = Color.RED;
                        break;
                    default:
                        color = null;
                        break;
                }
                if (color != null) {
                    Rectangle rectangle = new Rectangle(40, 40, color);
                    positionGridPaneEnemy.add(rectangle, col, row);
                }
            }
        }
    }
    public void addEvent(GridPane gridPane) {
        Image image = new Image("file:src/main/resources/org/example/navalbattle/images/water.png");
        for (int row = 0; row <= 9; row++) {
            for (int col = 0; col <= 9; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                imageView.setImage(image);
                gridPane.add(imageView  , col, row);

            }
        }
    }

}
