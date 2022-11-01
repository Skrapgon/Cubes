package main.java;

public class Builder {
    public static final String RESET = "\033[0m";

    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String PURPLE_BACKGROUND = "\033[45m";
    public static final String WHITE_BACKGROUND = "\033[47m";

    public static final String[] Colors = {
        WHITE_BACKGROUND + "0000" + RESET,
        RED_BACKGROUND + "1111" + RESET,
        BLUE_BACKGROUND + "2222" + RESET,
        YELLOW_BACKGROUND + "3333" + RESET,
        GREEN_BACKGROUND+ "4444" + RESET,
        PURPLE_BACKGROUND + "5555" + RESET,
    };

    public void printFigure(int[][] figure) {
        String res = new String();
        for (int i = 0; i < figure.length; i++) {
            String curStr = new String();
            for (int j = 0; j < figure[i].length; j++) curStr += Colors[figure[i][j]] + ' ';
            res += curStr + '\n' + curStr + "\n\n";
        }

        System.out.print(res);
    }
}
