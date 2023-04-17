package com.epicbattle.game.units;


public final class Lancer extends Melee {
    private static final String className = "Пикенер";

    public Lancer(String name, int x, int y) {
        super(className, name, 1, x, y, 10, 2,14, 6, new int[]{7, 13}, 15, 0, 4);

    }

}
