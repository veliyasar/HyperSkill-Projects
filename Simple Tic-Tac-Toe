package tictactoe;

import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    static char[][] table = new char[3][3];
    static int playCount = 0;

    public static void main(String[] args) {
        fillWithSpaces();
        printTable();
        while (playCount != 9) {
            getCoordinates();
            printTable();
            checkTable();
        }
    }


    static void fillWithSpaces() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = ' ';
            }
        }
    }


    static void getCoordinates() {

        while (true) {
            System.out.println("Enter the coordinates: ");
            var axisAinput = scanner.next();
            var axisBinput = scanner.next();

            if (!isNumber(axisAinput, axisBinput)) {
                System.out.println("You should enter numbers!");
                continue;
            }

            var axisA = Integer.parseInt(axisAinput);
            var axisB = Integer.parseInt(axisBinput);

            if (axisA < 1 || axisA > 3 || axisB < 1 || axisB > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            axisA--;
            axisB--;

            if (table[axisA][axisB] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (playCount % 2 == 0)
                table[axisA][axisB] = 'X';
            else
                table[axisA][axisB] = 'O';

            playCount++;
            break;
        }
    }


    static void checkTable() {
        int countX = 0, countO = 0;
        int totalCountX = 0, totalCountO = 0;
        int winCountX = 0, winCountO = 0;

        //horizontal
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                switch (table[i][j]) {
                    case 'X':
                        countX++;
                        totalCountX++;
                        if (countX == 3) winCountX++;
                        break;
                    case 'O':
                        countO++;
                        totalCountO++;
                        if (countO == 3) winCountO++;
                        break;
                }
            }
            countO = 0;
            countX = 0;
        }

        //vertical
        for (int i = 0; i < table[0].length; i++) {
            for (int j = 0; j < table.length; j++) {
                switch (table[j][i]) {
                    case 'X':
                        countX++;
                        if (countX == 3) winCountX++;
                        break;
                    case 'O':
                        countO++;
                        if (countO == 3) winCountO++;
                        break;
                }
            }
            countO = 0;
            countX = 0;
        }

        //diagonal right
        for (int i = 0, j = 2; j != -1; i++, j--) {
            switch (table[i][j]) {
                case 'X':
                    countX++;
                    if (countX == 3) winCountX++;
                    break;
                case 'O':
                    countO++;
                    if (countO == 3) winCountO++;
                    break;
            }
        }
        countO = 0;
        countX = 0;

        //diagonal left
        for (int i = 0, j = 0; j != 3 ; i++, j++) {
            switch (table[i][j]) {
                case 'X':
                    countX++;
                    if (countX == 3) winCountX++;
                    break;
                case 'O':
                    countO++;
                    if (countO == 3) winCountO++;
                    break;
            }
        }

        printResult(totalCountX, totalCountO, winCountX, winCountO);
    }


    static void printResult(int totalCountX, int totalCountO, int winCountX, int winCountO) {

        int totalDifference = Math.abs(totalCountX - totalCountO);

        //when no side has a three in a row and the grid has no empty cells
        if ((winCountO == 0 && winCountX == 0) && !hasEmptyCell()) {
            System.out.println("Draw");
            System.exit(0);
        } else
            //when the grid has three X’s in a row as well as three O’s in a row,
            // or there are a lot more X's than O's or vice versa
            if ((winCountO > 0 && winCountX > 0) || (totalDifference >= 2))
                System.out.println("Impossible");
        else
            //when neither side has three in a row but the grid still has empty cells
            if (hasEmptyCell() && winCountX == 0 && winCountO == 0)
                System.out.println("Game not finished");
        else
            if (winCountX > 0) {
                System.out.println("X wins");
                System.exit(0);
            } else if (winCountO > 0) {
                System.out.println("O wins");
                System.exit(0);
            }
    }


    static boolean hasEmptyCell() {

        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[0].length; j++){
                if(table[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }

    static boolean isNumber(String s1, String s2) {

        try {
            var s1int = Integer.parseInt(s1);
            var s2int = Integer.parseInt(s2);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static void printTable() {

        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", table[0][0], table[0][1], table[0][2]);
        System.out.printf("| %c %c %c |\n", table[1][0], table[1][1], table[1][2]);
        System.out.printf("| %c %c %c |\n", table[2][0], table[2][1], table[2][2]);
        System.out.println("---------");
    }
}
