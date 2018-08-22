package co.baq.tolosa.alvaro.minesweeper.cell;

import co.baq.tolosa.alvaro.minesweeper.MineSweeper;

import java.util.Arrays;
import java.util.List;

public abstract class Cell {
    private static final List<Character> VALID_STATUS = Arrays.asList('M', 'U');
    private int row;
    private int column;
    private char status;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.status = 'C';
    }

    public char getStatus() {
        return status;
    }

    public abstract boolean shouldBeMarked();

    public boolean changeStatus(char status) {
        if(!VALID_STATUS.contains(status)) {
            throw new IllegalArgumentException(String.format("Status %c is invalid. Valid are: %s", status, VALID_STATUS));
        }

        this.status = status;

        if (this.status== 'U') return execute();
        return true;
    }

    public abstract boolean execute() ;

    public abstract void print(MineSweeper mineSweeper);

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public abstract boolean canExplode();

    public int numberOfNeighbourMines(MineSweeper game) {
        int numberOfNeighbourMines = getNumberOfNeighbourMinesInRoof(game);

        numberOfNeighbourMines += getNumberOfNeighbourMinesInLeftAndRightColumn(game);
        numberOfNeighbourMines += getNumberOfNeighbourMinesInFloor(game);

        return numberOfNeighbourMines;
    }

    private int getNumberOfNeighbourMinesInFloor(MineSweeper game) {
        int columns = game.getNumberOfColumns();
        int rows = game.getNumberOfRows();

        int numberOfNeighbourMines = 0;

        if(getRow() < rows -1 && (game.getCell(getRow() + 1, getColumn()).canExplode())) {
            numberOfNeighbourMines ++;
        }

        if( getColumn() < columns-1 && getRow() < rows-1 && game.getCell(getRow() + 1, getColumn() + 1).canExplode()) {
            numberOfNeighbourMines ++;
        }

        if( getRow() < rows-1 && getColumn() > 0 && game.getCell(getRow() + 1, getColumn() - 1).canExplode()) {
            numberOfNeighbourMines ++;
        }

        return numberOfNeighbourMines;
    }

    private int getNumberOfNeighbourMinesInRoof(MineSweeper game) {
        int columns = game.getNumberOfColumns();

        int numberOfNeighbourMines = 0;

        if( getRow() > 0 && (game.getCell(getRow() -1, getColumn()).canExplode())) {
            numberOfNeighbourMines ++;
        }
        if( getColumn() > 0 && getRow() > 0 && game.getCell(getRow() -1, getColumn() - 1).canExplode()) {
            numberOfNeighbourMines ++;
        }
        if( getRow() > 0 && getColumn() < columns-1 && game.getCell(getRow() - 1, getColumn() + 1).canExplode()) {
            numberOfNeighbourMines ++;
        }
        return numberOfNeighbourMines;
    }

    private int getNumberOfNeighbourMinesInLeftAndRightColumn(MineSweeper game) {
        int columns = game.getNumberOfColumns();
        int numberOfNeighbourMines = 0;

        if( getColumn() > 0 && (game.getCell(getRow(), getColumn() - 1).canExplode())) {
            numberOfNeighbourMines ++;
        }

        if( getColumn() < columns-1 && (game.getCell(getRow(), getColumn() + 1).canExplode())) {
            numberOfNeighbourMines ++;
        }
        return numberOfNeighbourMines;
    }
}
