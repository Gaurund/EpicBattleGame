package com.epicbattle.game.units;


public class Side {
    private static final String[] sides = new String[]{"Белый", "Чёрный"};
    protected int sideID;

    public Side(int sideID) {
        this.sideID = sideID;
    }

    public int getSideID() {
        return sideID;
    }

    public String getSideName() {
        return sides[sideID];
    }
}
