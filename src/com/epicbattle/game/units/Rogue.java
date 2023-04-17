package com.epicbattle.game.units;


public final class Rogue extends Melee {
    private static final String className = "Разбойник";

    public Rogue(String name, int x, int y) {
        super(className, name, 0, x, y, 9, 2,15, 5, new int[]{8, 14}, 15, 0, 3);

    }

}
