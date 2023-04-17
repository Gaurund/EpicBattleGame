package com.epicbattle.game.units;

public final class Archer extends Ranged {
    private static final String className = "Лучник";

    public Archer(String name, int x, int y) {
        super(className, name, 0, x, y, 7, 7,12, 2, new int[]{6, 12}, 0, 5, 70);
    }


}
