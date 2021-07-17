package com.slinger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);
        controller.run();
    }
}
