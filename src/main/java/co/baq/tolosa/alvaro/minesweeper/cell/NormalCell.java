package co.baq.tolosa.alvaro.minesweeper.cell;

import co.baq.tolosa.alvaro.minesweeper.MineSweeper;

public class NormalCell extends Cell {

    public NormalCell(int row, int column) {
        super(row, column);
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public boolean shouldBeMarked() {
        return getStatus() != 'M';
    }

    @Override
    public void print(MineSweeper game) {
        if(this.getStatus() == 'U') {
            int bombs = numberOfNeighbourMines(game);
            if (bombs > 0) {
                System.out.print(bombs+ " ");
            } else {
                System.out.print( "- ");
            }

        } else if (this.getStatus() == 'M'){
            System.out.print("P ");
        } else {
            System.out.print(". ");
        }
    }

    @Override
    public boolean canExplode() {
        return false;
    }
}
