package cinema;

import java.util.Locale;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        //Locale.setDefault(new Locale("en", "US"));
        var scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rowCount = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatCount = scanner.nextInt();

        double NUMBEROFSEATS = rowCount * seatCount;

        int TOTALINCOME = 0;
        int sumOfIncome = 0;

        if(rowCount * seatCount < 60) {
            TOTALINCOME = rowCount * seatCount * 10;
        } else {
            TOTALINCOME = rowCount * (seatCount - 1) * 10;
        }

        var purchasedTicketCount = 0;

        var matrixOfSeats = new String[++rowCount][++seatCount];

        //filling the matrixOfSeats
        for (int i = 0; i < seatCount; i++) {
            matrixOfSeats[0][i] = String.valueOf(i);
            for (int j = 1; j < rowCount; j++)
                matrixOfSeats[j][i] = "S";
        }
        for (int i = 1; i < rowCount; i++) {
            matrixOfSeats[i][0] = String.valueOf(i);
        }
        matrixOfSeats[0][0] = " ";

        var response = 1;
        while (response != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            var income = 0;
            response = scanner.nextInt();

            switch (response) {
                case 1:
                    ShowTheSeats(seatCount, rowCount, matrixOfSeats);
                    break;
                case 2:
                    purchasedTicketCount++;
                    sumOfIncome += BuyTicket(seatCount, rowCount, matrixOfSeats, income);
                    break;
                case 3:
                    PrintTheStats(purchasedTicketCount, NUMBEROFSEATS, sumOfIncome, TOTALINCOME);
                    break;
            }
        }
    }

    public static void ShowTheSeats(int seatCount, int rowCount, String[][] matrixOfSeats) {

        System.out.println("Cinema:");
        for (var i = 0; i < matrixOfSeats.length; i++)
        {
            for (var j = 0; j < matrixOfSeats[0].length; j++)
            {
                System.out.print((matrixOfSeats[i][j] + " "));
            }
            System.out.println();
        }
    }

    public static int BuyTicket(int seatCount, int rowCount, String[][] matrixOfSeats, int income) {

        var scanner = new Scanner(System.in);
        int thRow, thSeat;

        while (true) {
            System.out.println("Enter a row number:");
            thRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            thSeat = scanner.nextInt();

            try {
                if (matrixOfSeats[thRow][thSeat].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
                continue;
            }

            break;
        }

        int frontHalf = rowCount / 2;
        int customPrice;
        if (rowCount * seatCount < 60 || thRow < frontHalf)
            customPrice = 10;
        else customPrice = 8;

        System.out.println("Ticket price: $" + customPrice);

        matrixOfSeats[thRow][thSeat] = "B";
        income += customPrice;

        return income;
    }

    public static void PrintTheStats(int purchasedTicketCount, double NUMBEROFSEATS, int sumOfIncome, int TOTALINCOME) {

        double percentage = (purchasedTicketCount / NUMBEROFSEATS) * 100;

        System.out.println("Number of purchased tickets: " + purchasedTicketCount);
        System.out.printf("Percentage: %.2f%c%n", percentage, '%');
        System.out.println("Current income: $" + sumOfIncome);
        System.out.println("Total income: $" + TOTALINCOME);
    }
}
