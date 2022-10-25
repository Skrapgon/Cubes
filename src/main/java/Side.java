package main.java;

import java.util.Arrays;

public class Side {

    private Side up, down, right, left;
    private int[][] sideContent;


    public Side(int[][] sideContent) {
        this.sideContent = sideContent;
    }

    public void setAdjacentSides(Side up, Side down, Side right, Side left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public void rotateAdjacentSides(String direction) {
        Side tmp = this.up;
        if (direction.equals("left")) {
            this.up = this.left;
            this.left = this.down;
            this.down = this.right;
            this.right = tmp;
            this.rotateMatrix(direction);
        }
        else if (direction.equals("right")) {
            this.up = this.right;
            this.right = this.down;
            this.down = this.left;
            this.left = tmp;
            this.rotateMatrix(direction);
        }
        else if (direction.equals("vertical")) {
            this.up = this.down;
            this.down = tmp;
        }
        else if (direction.equals("horizontal")) {
            tmp = this.left;
            this.left = this.right;
            this.right = tmp;
        }
    }

    public void rotateMatrix(String direction) {
        int[][] tmp = new int[this.sideContent.length][];
        for (int i = 0; i < tmp.length; i++) tmp[i] = Arrays.copyOf(this.sideContent[i], this.sideContent[i].length);

        if (direction.equals("left")) {
            this.sideContent[0][0] = tmp[2][0];
            this.sideContent[0][1] = tmp[1][0];
            this.sideContent[0][2] = tmp[0][0];
            this.sideContent[1][0] = tmp[2][1];
            this.sideContent[1][2] = tmp[0][1];
            this.sideContent[2][0] = tmp[2][2];
            this.sideContent[2][1] = tmp[1][2];
            this.sideContent[2][2] = tmp[0][2];
        }
        else if (direction.equals("right")) {
            this.sideContent[0][0] = tmp[0][2];
            this.sideContent[0][1] = tmp[1][2];
            this.sideContent[0][2] = tmp[2][2];
            this.sideContent[1][0] = tmp[0][1];
            this.sideContent[1][2] = tmp[2][1];
            this.sideContent[2][0] = tmp[0][0];
            this.sideContent[2][1] = tmp[1][0];
            this.sideContent[2][2] = tmp[2][0];
        }
    }

    public void changeRow(int row, String direction, int[] swap, int count) {
        if (count < 4) {
            int[] tmp = Arrays.copyOf(this.sideContent[row], this.sideContent[row].length);

            this.sideContent[row] = Arrays.copyOf(swap, 3);

            if (direction.equals("left")) this.left.changeRow(row, direction, tmp, count+1);
            else this.right.changeRow(row, direction, tmp, count+1);
        }
        if (row == 0) this.up.rotateMatrix(direction);
        else if (row == 2) this.down.rotateMatrix(direction);
    }

    public void changeColumn(int column, String direction, int[] swap, int count) {
        if (count < 4) {
            int[] tmp = new int[3];
            tmp[0] = this.sideContent[0][column];
            tmp[1] = this.sideContent[1][column];
            tmp[2] = this.sideContent[2][column];

            this.sideContent[0][column] = swap[0];
            this.sideContent[1][column] = swap[1];
            this.sideContent[2][column] = swap[2];

            if(direction.equals("up")) this.up.changeColumn(column, direction, tmp, count+1);
            else this.down.changeColumn(column, direction, tmp, count+1);
        }
        if (column == 0) {
            if (direction.equals("up")) this.left.rotateMatrix("right");
            else this.left.rotateMatrix("left");
        }
        if (column == 2) {
            if (direction.equals("up")) this.right.rotateMatrix("left");
            else this.right.rotateMatrix("right");
        }
    }

    public boolean checkContentSide() {
        int choose = this.sideContent[0][0];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (choose != this.sideContent[i][j]) return false;
        return true;
    }

    public int[][] getMatrix() {
        return this.sideContent;
    }

    public void printSide() {
        String row1 = String.format(
            "%s %s %s\n%s %s %s\n",
            Colors.Colors[this.sideContent[0][0]],
            Colors.Colors[this.sideContent[0][1]],
            Colors.Colors[this.sideContent[0][2]],
            Colors.Colors[this.sideContent[0][0]],
            Colors.Colors[this.sideContent[0][1]],
            Colors.Colors[this.sideContent[0][2]]
        );

        String row2 = String.format(
            "%s %s %s\n%s %s %s\n",
            Colors.Colors[this.sideContent[1][0]],
            Colors.Colors[this.sideContent[1][1]],
            Colors.Colors[this.sideContent[1][2]],
            Colors.Colors[this.sideContent[1][0]],
            Colors.Colors[this.sideContent[1][1]],
            Colors.Colors[this.sideContent[1][2]]
        );

        String row3 = String.format(
            "%s %s %s\n%s %s %s",
            Colors.Colors[this.sideContent[2][0]],
            Colors.Colors[this.sideContent[2][1]],
            Colors.Colors[this.sideContent[2][2]],
            Colors.Colors[this.sideContent[2][0]],
            Colors.Colors[this.sideContent[2][1]],
            Colors.Colors[this.sideContent[2][2]]
        );

        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
    }

    public Side getSide(String side){
        if (side.equals("up")) return this.up;
        if (side.equals("down")) return this.down;
        if (side.equals("right")) return this.right;
        return this.left;
    }

    public int[] getRow(int row) {
        return this.sideContent[row];
    }

    public int[] getColumn(int column) {
        int[] res = new int[3];
        res[0] = this.sideContent[0][column];
        res[1] = this.sideContent[1][column];
        res[2] = this.sideContent[2][column];
        return res;
    }
}