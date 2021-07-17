package com.slinger;

import java.util.Scanner;

public class Controller {

    private final Scanner scanner;
    private final BaseConverter baseConverter;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
        this.baseConverter = new BaseConverter();
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            String input = scanner.nextLine().trim();

            if ("/exit".equals(input.toLowerCase().trim())) {
                isRunning = false;
                continue;
            }

            if (!isValidInput(input)) {
                continue;
            }

            //At this point input is valid so can safely parse to ints
            int sourceBase = Integer.parseInt(input.split("\\s+")[0]);
            int targetBase = Integer.parseInt(input.split("\\s+")[1]);
            boolean isConverting = true;

            while (isConverting) {
                isConverting = convert(sourceBase, targetBase);
            }
        }
    }

    /*
    * Converts to a source base to a target base and prints it
    * returns false if user entered 'quit'
    */
    public boolean convert(int sourceBase, int targetBase) {
        String finalResult = "";

        System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
        String input = scanner.nextLine().toLowerCase().trim();

        if ("back".equals(input) || "/back".equals(input)) {
            System.out.println();
            return false;
        }

        //get integer and decimal part
        String[] holder = input.split("\\.");
        String integerPart = holder[0];
        String decimalPart = holder.length == 2 ? holder[1] : null;

        //check if we have an integer part
        if (integerPart.length() != 0) {
            //first convert source base to base10
            String toBase10 = baseConverter.convertToBase10(integerPart, sourceBase);

            //now convert base10 to target base and print it
            finalResult= baseConverter.convertDecimal(toBase10, targetBase);
        }

        //now check if we need to deal with fraction parts
        if (decimalPart != null) {
            //convert fraction part to base10
            String toBase10Fraction = baseConverter.convertFractionToBase10(decimalPart, sourceBase);

            //now convert base10 fraction part to target base
            String toTargetBaseFraction = "." +  baseConverter.convertDecimalFraction(toBase10Fraction, targetBase);


            //add fraction to already converted number
            finalResult += toTargetBaseFraction;
        }

        System.out.println("Conversion result: " + finalResult + "\n");

        return true;
    }


    /*
    *Tests if user input is valid
    *Since there are 26 Latin letters and 10 digits, the maximum base is 26 + 10 = 36.
    * So, the target and source base will be between 2 and 36.
    */
    private boolean isValidInput(String input) {
        String[] holder = input.split("\\s+");

        if (holder.length != 2) {
            System.out.println("Error! Must enter two numbers! Try again!");
            return false;
        }

        //regex range from number 2 - 36
        String range = "^([2-9]|1[0-9]|2[0-9]|3[0-6])$";
        if (!holder[0].matches(range) || !holder[1].matches(range)) {
            System.out.println("Error! Bases must be number from 2-36. Try again!");
            return false;
        }

        return true;
    }

    private void printMenu() {
        System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
    }

}
