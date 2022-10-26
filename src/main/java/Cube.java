package main.java;

public class Cube {

    private Side side1;
    private Side side2;
    private Side side3;
    private Side side4;
    private Side side5;
    private Side side6;
    private Side currentSide;

    public Cube() {
        this.side1 = new Side(new int[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
            }
        );

        this.side2 = new Side(new int[][] {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
            }
        );

        this.side3 = new Side(new int[][] {
                {2, 2, 2},
                {2, 2, 2},
                {2, 2, 2}
            }
        );

        this.side4 = new Side(new int[][] {
                {3, 3, 3},
                {3, 3, 3},
                {3, 3, 3}
            }
        );

        this.side5 = new Side(new int[][] {
                {4, 4, 4},
                {4, 4, 4},
                {4, 4, 4}
            }
        );

        this.side6 = new Side(new int[][] {
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5}
            }
        );

        side1.setAdjacentSides(side4, side5, side2, side3);
        side2.setAdjacentSides(side4, side5, side6, side1);
        side3.setAdjacentSides(side4, side5, side1, side6);
        side4.setAdjacentSides(side6, side1, side2, side3);
        side5.setAdjacentSides(side1, side6, side2, side3);
        side6.setAdjacentSides(side5, side4, side3, side2);

        currentSide = side1;
    }

    public void rotateLeft() {
        this.currentSide.getSide("left").rotateAdjacentSides("vertical");
        this.currentSide.getSide("left").getSide("left").rotateAdjacentSides("vertical");
        this.currentSide.getSide("up").rotateAdjacentSides("left");
        this.currentSide.getSide("down").rotateAdjacentSides("right");
        this.currentSide = this.currentSide.getSide("right");
    }

    public void rotateRight() {
        this.currentSide.getSide("right").rotateAdjacentSides("vertical");
        this.currentSide.getSide("right").getSide("right").rotateAdjacentSides("vertical");
        this.currentSide.getSide("up").rotateAdjacentSides("right");
        this.currentSide.getSide("down").rotateAdjacentSides("left");
        this.currentSide = this.currentSide.getSide("left");
    }

    public void rotateUp() {
        this.currentSide.getSide("up").rotateAdjacentSides("horizontal");
        this.currentSide.getSide("up").getSide("up").rotateAdjacentSides("horizontal");
        this.currentSide.getSide("left").rotateAdjacentSides("right");
        this.currentSide.getSide("right").rotateAdjacentSides("left");
        this.currentSide = this.currentSide.getSide("down");
    }

    public void rotateDown() {
        this.currentSide.getSide("down").rotateAdjacentSides("horizontal");
        this.currentSide.getSide("down").getSide("down").rotateAdjacentSides("horizontal");
        this.currentSide.getSide("left").rotateAdjacentSides("left");
        this.currentSide.getSide("right").rotateAdjacentSides("right");
        this.currentSide = this.currentSide.getSide("up");
    }

    public void rotate(String direction) {
        if (direction.equals("left")) this.rotateLeft();
        else if (direction.equals("right")) this.rotateRight();
        else if (direction.equals("up")) this.rotateUp();
        else if (direction.equals("down")) this.rotateDown();
    }

    public Side getCurrentSide() {
        return this.currentSide;
    }

    public void changeRow(int index, String direction) {
        if (direction.equals("left")) currentSide.changeRow(index, "left", this.currentSide.getSide("right").getRow(index), 0);
        else currentSide.changeRow(index, "right", this.currentSide.getSide("left").getRow(index), 0);
    }

    public void changeColumn(int index, String direction) {
        if (direction.equals("up")) currentSide.changeColumn(index, "up", this.currentSide.getSide("down").getColumn(index), 0);
        else currentSide.changeColumn(index, "down", this.currentSide.getSide("up").getColumn(index), 0);
    }

    public boolean checkBuild() {
        if (this.side1.checkContentSide() && this.side2.checkContentSide() && this.side3.checkContentSide()
        && this.side4.checkContentSide() && this.side5.checkContentSide() && this.side6.checkContentSide()) return true;
        return false;
    }
}