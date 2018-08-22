package co.baq.tolosa.alvaro.minesweeper;

import co.baq.tolosa.alvaro.minesweeper.cell.Cell;
import co.baq.tolosa.alvaro.minesweeper.cell.NormalCell;

public class MineSweeper {
    private int rows;
    private int columns;

    private Cell[][] cells;

    private boolean isGameOver;

    public MineSweeper(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        cells = new Cell[rows][columns];

        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                cells[i][j] = new NormalCell(i, j);
            }
        }
    }

    public void setCell(int row, int column, Cell cell) {
        cells[row][column] = cell;
    }

    public void uncover(int row, int column) {
        if (row < 0 || column < 0 || this.rows < row || this.columns < column) {
            throw new IllegalArgumentException(String.format("The selected cell (%d, %d) is not in the game", row, column));
        }
        isGameOver = !cells[row][column].changeStatus('U');
    }

    public void mark(int row, int column) {
        if (row < 0 || column < 0 || this.rows < row || this.columns < column) {
            throw new IllegalArgumentException(String.format("The selected cell (%d, %d) is not in the game", row, column));
        }
        cells[row][column].changeStatus('M');
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isGameWon() {
        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                if(!cells[i][j].shouldBeMarked()) return false;
            }
        }
        isGameOver = true;
        return true;
    }

    public void print() {
        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                printCell(cells[i][j]);
            }
            System.out.println();
        }
    }

    private void printCell(Cell cell) {
        cell.print(this);
    }

    public int getNumberOfRows() {
        return rows;
    }

    public int getNumberOfColumns() {
        return columns;
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }
}
