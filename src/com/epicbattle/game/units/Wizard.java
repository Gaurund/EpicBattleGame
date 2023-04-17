package com.epicbattle.game.units;


public final class Wizard extends Healer {

    private static final String className = "Волшебник";

    public Wizard(String name, int x, int y) {
        super(className, name, 1, x, y, 5, 7, 12, 1, new int[]{6, 12}, 30, 4);
    }

}
