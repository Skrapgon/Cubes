package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static void help() {
        System.out.println("Кубик рубика 2D");
        System.out.println("Для поворота кубика/горизонтального ряда налево введите \"a\"");
        System.out.println("Для поворота кубика/горизонтального ряда направо введите \"d\"");
        System.out.println("Для поворота кубика/вертикального ряда вверх введите \"w\"");
        System.out.println("Для поворота кубика/вертикального ряда вниз введите \"s\"");
        System.out.println("Для выхода введите \"-\"");
    }

    public static void helpVerticalRotate() {
        System.out.println("Для поворота кубика введите \"r\"");
        System.out.println("Для поворота вертикального ряда введите номер столбца (нумерация начинается с 0 и идет до 2 включительно)");
    }

    public static void helpHorizontalRotate() {
        System.out.println("Для поворота кубика введите \"r\"");
        System.out.println("Для поворота горизонтального ряда введите номер строки (нумерация начинается с 0 и идет до 2 включительно)");
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch(Exception E) {
                System.out.println(E);
            }
    }

    public static void main(String[] args) throws IOException {

        HashMap<String, String> soo = new HashMap<>();
        soo.put("a", "left");
        soo.put("d", "right");
        soo.put("w", "up");
        soo.put("s", "down");

        String[][] directions = new String[][] {
            {"left", "right"},
            {"up", "down"}
        };
        
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
            if (cORr == 0) cubic.changeRow(index, directions[0][direction]);
            else cubic.changeColumn(index, directions[1][direction]);
        }
        Builder builder = new Builder();
        String m;
        Scanner scanner = new Scanner(System.in);
        do {
            cls();
            help();
            builder.printFigure(cubic.getCurrentSide().getMatrix());
            System.out.print("Выберите действие: ");
            m = scanner.nextLine();
            
            switch (m) {
                case ("a"):
                case ("d"):
                    helpHorizontalRotate();
                    String vr = scanner.nextLine();
                    switch (vr) {
                        case ("r"):
                            cubic.rotate(soo.get(m));
                            break;
                        case ("0"):
                        case ("1"):
                        case ("2"):
                            cubic.changeRow(Integer.valueOf(vr), soo.get(m));
                            break;
                    }
                    break;
                case ("w"):
                case("s"):
                    helpVerticalRotate();
                    String vc = scanner.nextLine();
                    switch (vc) {
                        case ("r"):
                            cubic.rotate(soo.get(m));
                            break;
                        case ("0"):
                        case ("1"):
                        case ("2"):
                            cubic.changeColumn(Integer.valueOf(vc), soo.get(m));
                            break;
                    }
                    break;
                case ("-"):
                    break;
                }
            cls();
        } while (!cubic.checkBuild() && !m.equals("-"));

        if (cubic.checkBuild()) {
            System.out.println("Поздравляем! Вы смогли собрать кубик рубика");
            builder.printFigure(cubic.getCurrentSide().getMatrix());
        }

        else System.out.println("Вы вышли из программы.");
    }
}
