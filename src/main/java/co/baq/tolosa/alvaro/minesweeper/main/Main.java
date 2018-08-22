package co.baq.tolosa.alvaro.minesweeper.main;

import co.baq.tolosa.alvaro.minesweeper.MineSweeper;
import co.baq.tolosa.alvaro.minesweeper.cell.MineCell;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please indicate the board’s height, width, and number of mines: ");
        int rows = scanner.nextInt();

        int columns = scanner.nextInt();

        int mines = scanner.nextInt();

        MineSweeper mineSweeper = new MineSweeper(rows, columns);

        int mineRowPosition;
        int mineColumnPosition;

        Set<String> minePosition = new HashSet<>();

        while(minePosition.size() < mines) {
            mineRowPosition = ThreadLocalRandom.current().nextInt(rows);
            mineColumnPosition = ThreadLocalRandom.current().nextInt(columns);
            minePosition.add(mineRowPosition+"_"+mineColumnPosition);
        }

        for(String position:minePosition) {
            String []parts = position.split("_");
            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);
            mineSweeper.setCell(row, column, new MineCell(row, column));
        }

        mineSweeper.print();

        while(!mineSweeper.isGameOver() && !mineSweeper.isGameWon()) {
            System.out.print("Please indicate your next move (row column action): ");
            String strRow = scanner.next();
            String strCol = scanner.next();
            String action = scanner.next();

            int row = Integer.parseInt(strRow);
            int column = Integer.parseInt(strCol);
            switch (action) {
                case "U":
                    mineSweeper.uncover(row, column);
                    break;
                case "M":
                    mineSweeper.mark(row, column);
                    break;
                default:
                    System.out.println("Action unknown:"+action);
            }
            mineSweeper.print();
        }

        if(mineSweeper.isGameWon()) {
            System.out.println("Congratulations, you WON!!!");
        } else {
            System.out.println("I BEAT you!!!");
        }


    }
}
