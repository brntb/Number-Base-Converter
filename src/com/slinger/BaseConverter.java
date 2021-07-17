package com.slinger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BaseConverter {

    /*
    * converts a base 10 number to number in given base
    */
    public String convertDecimal(String toConvert, int targetBase) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        BigInteger numberToConvert = new BigInteger(toConvert);

        while (numberToConvert.signum() == 1) {
            int remainder = numberToConvert.remainder(BigInteger.valueOf(targetBase)).intValue();
            String toAdd = remainder > 9 ? getLetter(remainder) : "" + remainder;
            stack.push(toAdd);
            numberToConvert = numberToConvert.divide(BigInteger.valueOf(targetBase));
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    /*
    * Converts a fraction in base 10 to given base
    */
    public String convertDecimalFraction(String toConvert, int targetBase) {
        Queue<String> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        BigDecimal numberToConvert = new BigDecimal(toConvert);
        BigDecimal target = BigDecimal.valueOf(targetBase);

        //only keeping 5 decimal parts
        int parts = 0;

        while (parts++ < 5) {
            numberToConvert = numberToConvert.multiply(target);

            //add integer to left of decimal to queue
            String toLeft = numberToConvert.toString().split("\\.")[0];

            //check for numbers greater than 10
            int toLeftInt = Integer.parseInt(toLeft);
            String toAdd = toLeftInt > 9 ? getLetter(toLeftInt) : "" + toLeftInt;

            queue.add(toAdd);

            //adjust number to convert
            String toRight = numberToConvert.toString().split("\\.")[1];
            numberToConvert = new BigDecimal("." + toRight);
        }

        while (!queue.isEmpty()) {
            result.append(queue.remove());
        }

        return result.toString();
    }

    /*
    * converts a number in a different base back to base 10
    */
    public String convertToBase10(String numberToConvert, int base) {
        String[] digits = numberToConvert.split("");

        //theres a chance that digits could be letters, if it is a letter we want it lowercase
        //for example 25A should be changed to 25a
        toLowerCase(digits);

        BigInteger result = BigInteger.ZERO;
        int power = digits.length - 1;

        for (String current : digits) {
            int num = current.matches("[a-z]") ? getDigit(current) : Integer.parseInt(current);
            long toAdd = (long) (num * Math.pow(base, power--));
            result = result.add(new BigInteger("" + toAdd));
        }

        return result.toString();
    }

    /*
     * converts a fraction in a different base back to base 10
     */
    public String convertFractionToBase10(String fractionToConvert, int base) {
        String[] digits = fractionToConvert.split("");

        //theres a chance that digits could be letters, if it is a letter we want it lowercase
        //for example 25A should be changed to 25a
        toLowerCase(digits);

        BigDecimal result = BigDecimal.ZERO;
        int power = -1;

        for (String current : digits) {
            int num = current.matches("[a-z]") ? getDigit(current) : Integer.parseInt(current);
            double toAdd = num * Math.pow(base, power--);
            result = result.add(new BigDecimal("" + toAdd));
        }

        return result.toString();
    }


    /*
    * returns uppercase letter that corresponds to digits past 10 for higher bases
    * 10 - > A, 11 -> B , 12 -> C ... and so on
    */
    public String getLetter(int number) {
        char letter = (char) (55 + number);
        return String.valueOf(letter);
    }

    /*
    * returns lowercase letter to digit for higher bases
    * a -> 10, b -> 11 .... and so on
    */
    public int getDigit(String letterToConvert) {
        int num = letterToConvert.charAt(0);
        return num - 87;
    }

    /*
    * takes an array of digits and letters that correspond to number in some base
    * if there is a letter, it turns it to lowercase
    */
    private void toLowerCase(String[] array) {
        for (int i = 0; i < array.length; i++) {
            String current = array[i];
            if (current.matches("[A-Z]")) {
                array[i] = array[i].toLowerCase();
            }
        }
    }

}
