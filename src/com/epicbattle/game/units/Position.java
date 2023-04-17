package com.epicbattle.game.units;


import java.util.ArrayList;

public class Position {
    private final int[] positionArr;

    public Position(int x, int y) {
        this.positionArr = new int[2];
        this.positionArr[0] = x;
        this.positionArr[1] = y;
    }

    public int getX() {
        return positionArr[0];
    }

    public int getY() {
        return positionArr[1];
    }

    @Override
    public String toString() {
        return "Позиция (" +
                "x = " + this.getX() +
                ", y = " + this.getY() +
                ')';
    }

    public int[] getDirection(BaseHero enemy) {
        int enemyX = enemy.position.getX();
        int enemyY = enemy.position.getY();
        return new int[]{enemyX - getX(), enemyY - getY()};
    }

    public float getDistance(BaseHero enemy) {
        int enemyX = enemy.position.getX();
        int enemyY = enemy.position.getY();
        return (float) Math.sqrt(((getX() - enemyX) * (getX() - enemyX)) + ((getY() - enemyY) * (getY() - enemyY)));
    }


    public void move(BaseHero enemy, ArrayList<BaseHero> armies) {
        int[] direction = getDirection(enemy);
        int x = direction[0];
        int y = direction[1];
        int leftOrRight = (int) Math.signum(x);
        int upOrDown = (int) Math.signum(y);
        if (Math.abs(x) >= Math.abs(y)) {
            moving(0, 0, 1, leftOrRight, armies);
        } else {
            moving(0, 1, 0, upOrDown, armies);
        }
    }

    private void moving(int counter, int mainDimension, int offDimension, int sign, ArrayList<BaseHero> armies) {
        if (checkBorders(mainDimension, sign) && checkCell(mainDimension, offDimension, sign, armies)) {
            positionArr[mainDimension] += sign;
            return;
        }
        counter += 1;
        if (counter == 1) {
            moving(counter, offDimension, mainDimension, sign, armies);
        } else if (counter == 2) {
            moving(counter, mainDimension, offDimension, -sign, armies);
        }
    }

    private boolean checkCell(int mainDimension, int offDimension, int sign, ArrayList<BaseHero> armies) {
        int result = positionArr[mainDimension] + sign;
        for (BaseHero e : armies) {
            if (!e.isDead() && e.position.positionArr[offDimension] == positionArr[offDimension] && e.position.positionArr[mainDimension] == result) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBorders(int dimension, int sign) {
        int result = positionArr[dimension] + sign;
        return result > 0 && result < 11;
    }

}
