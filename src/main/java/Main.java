package main.java;

import java.io.IOException;
import java.util.Random;

public class Main {
    
    public static void help() {
        System.out.println("Кубик рубика 2D");
        System.out.println("Для поворота кубика налево введите \"a\"");
        System.out.println("Для поворота кубика направо введите \"d\"");
        System.out.println("Для поворота кубика вверх введите \"w\"");
        System.out.println("Для поворота кубика вниз введите \"s\"");
        System.out.println("Для поворота столбца введите \"e\"");
        System.out.println("Для поворота строки введите \"q\"");
        System.out.println("Для выхода введите \"-\"");
    }

    public static void helpRow() {
        System.out.println("Для поворота строки влево введите \"a\"");
        System.out.println("Для поворота строки вправо введите \"d\"");
    }

    public static void helpColumn() {
        System.out.println("Для поворота столбца вверх введите \"w\"");
        System.out.println("Для поворота столбца вниз введите \"s\"");
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch(Exception E) {
                System.out.println(E);
            }
    }

    public static void main(String[] args) throws IOException {
        
        Cube cubic = new Cube();

        Random randomIndex = new Random();
        Random randomCR = new Random();
        Random randomDirection = new Random();
        int cORr = -1;
        int index = -1;
        int direction = -1;

        for (int i = 0; i < 16; i++) {
            int tmp = cORr;
            cORr = randomCR.nextInt(2);
            if (cORr == tmp) cORr = (cORr + 1) % 2;
            tmp = index;
            index = randomIndex.nextInt(3);
            if (index == tmp) index = (index + 1) % 3;
            direction = randomDirection.nextInt(2);
            if (cORr == 0) cubic.changeRow(index, direction);
            else cubic.changeColumn(index, direction);
        }

        String m = "";
        do {
            help();
            cubic.getCurrentSide().printSide();
            System.out.print("Выберите действие: ");
            m = System.console().readLine();
            
            switch (m) {
                case ("a"):
                    cubic.rotateLeft();
                    break;
                case ("d"):
                    cubic.rotateRight();
                    break;
                case ("w"):
                    cubic.rotateUp();
                    break;
                case("s"):
                    cubic.rotateDown();
                    break;
                case ("e"):
                    helpColumn();
                    System.out.print("Выберите номер: ");
                    char num = (char) System.in.read();
                    switch (num) {
                        case ('0'):
                    }
                    break;
                case ("q"):
                    helpRow();
                    break;
                case ("-"):
                    break;
                }
            cls();
        } while (!cubic.checkBuild() && !m.equals("-"));

        System.out.println(cubic.checkBuild());
    }
}
