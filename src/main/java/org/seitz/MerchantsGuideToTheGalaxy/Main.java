package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IOHandler handler = new IOHandler();
        InputParser parser = new InputParser();
        boolean cont = true;

        while (cont) {
            Scanner scanner = new Scanner(System.in);
            cont = handler.readAndWrite(scanner, parser);
        }
    }
}