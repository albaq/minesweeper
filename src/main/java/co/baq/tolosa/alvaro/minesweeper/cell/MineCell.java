package co.baq.tolosa.alvaro.minesweeper.cell;

import co.baq.tolosa.alvaro.minesweeper.MineSweeper;

public class MineCell extends Cell {
    public MineCell(int row, int column) {
        super(row, column);
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void print(MineSweeper game) {
        if(this.getStatus() == 'U') {
            System.out.print("* ");
        } else if (this.getStatus() == 'M'){
            System.out.print("P ");
        } else {
            if(!game.isGameWon() && game.isGameOver()) {
                System.out.print("* ");
            } else {
                System.out.print(". ");
            }

        }
    }

    @Override
    public boolean shouldBeMarked() {
        return getStatus() == 'M';
    }

    @Override
    public boolean canExplode() {
        return true;
    }
}
