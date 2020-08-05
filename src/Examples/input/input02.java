
/*
    Morgage Calculator
*/

import java.text.NumberFormat;
import java.util.Scanner;

public class input02 {
    public static void main(String[] args) {
        final byte MONTH_IN_YEAR = 12;
        final byte PERCNT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: "); // 100,000
        int principal = scanner.nextInt();

        System.out.print("Annual Interest Rate: "); // 3.92
        float anualInterest = scanner.nextFloat();
        float monthlyInterest = anualInterest / PERCNT / MONTH_IN_YEAR;

        System.out.print("Period (Years): "); // 30
        byte years = scanner.nextByte();
        int numberOfPayments = years * MONTH_IN_YEAR;

        double morgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        String morgageFormatted = NumberFormat.getCurrencyInstance().format(morgage);

        System.out.println("Morgage: " + morgageFormatted); // 472.81
        scanner.close();
    }
}