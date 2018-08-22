package co.baq.tolosa.alvaro.minesweeper;

import co.baq.tolosa.alvaro.minesweeper.cell.MineCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MineSweeperTest {
    private MineSweeper sweeper;

    @BeforeEach
    void setUp() {
        sweeper = new MineSweeper(2, 2);
        sweeper.setCell(0, 0, new MineCell(0, 0));
        sweeper.setCell(1, 1, new MineCell(1, 1));
    }

    @Test
    void testingAProblemWithNumberOfNeighbourMines () {
        sweeper = new MineSweeper(3, 4);
        sweeper.setCell(2, 1, new MineCell(2, 1));
        sweeper.setCell(2, 3, new MineCell(2, 3));

        sweeper.uncover(0, 0);
        sweeper.uncover(0, 1);
        sweeper.uncover(0, 2);
        sweeper.uncover(0, 3);

        sweeper.uncover(1, 0);
        sweeper.uncover(2, 2);

        sweeper.uncover(2, 1);

        sweeper.print();
    }

    @Test
    void printGameWithMineCellUncover() {
        sweeper.uncover(0, 0);
        sweeper.print();
    }

    @Test
    void printGameWithMineCellMarked() {
        sweeper.mark(0, 0);
        sweeper.print();
    }

    @Test
    void printGameWithNormalCellMarked() {
        sweeper.mark(1, 0);
        sweeper.print();
    }

    @Test
    void printGameWithNormalCellUncoverNoAdjacentMines() {
        sweeper = new MineSweeper(2, 2);
        sweeper.uncover(1, 0);
        sweeper.print();
    }

    @Test
    void printGameWithNormalCellUncoverAnd2AdjacentMine() {
        sweeper.uncover(1, 0);
        sweeper.print();
    }

    @Test
    void whenUncoverAMine_theGameShouldTerminate() {
        sweeper.uncover(0, 0);

        assertFalse(sweeper.isGameWon());
        assertTrue(sweeper.isGameOver());
        sweeper.print();
    }

    @Test
    void whenUncoverANormalCell_theGameShouldContinue() {
        sweeper.uncover(1, 0);
        assertFalse(sweeper.isGameOver());
        assertFalse(sweeper.isGameWon());
    }

    @Test
    void whenMarkingAllMines_theGameIsWonAndOver() {
        sweeper.mark(0, 0);
        sweeper.mark(1, 1);

        assertTrue(sweeper.isGameWon());
        assertTrue(sweeper.isGameOver());
    }

    @Test
    void whenMarkingAllMinesAndSomeNormalCells_theGameIsNotWon() {
        sweeper.mark(0, 0);
        sweeper.mark(0, 1);
        sweeper.mark(1, 1);

        assertFalse(sweeper.isGameWon());
        assertFalse(sweeper.isGameOver());
    }
}