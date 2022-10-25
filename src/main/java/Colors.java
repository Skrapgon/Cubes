package main.java;

public class Colors {
    public static final String RESET = "\033[0m";

    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String PURPLE_BACKGROUND = "\033[45m";
    public static final String WHITE_BACKGROUND = "\033[47m";

    public static final String[] Colors = {
        WHITE_BACKGROUND + "00 00" + RESET,
        RED_BACKGROUND + "11 11" + RESET,
        BLUE_BACKGROUND + "22 22" + RESET,
        YELLOW_BACKGROUND + "33 33" + RESET,
        GREEN_BACKGROUND+ "44 44" + RESET,
        PURPLE_BACKGROUND + "55 55" + RESET,
    };
}
