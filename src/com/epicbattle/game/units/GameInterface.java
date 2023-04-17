package com.epicbattle.game.units;


import java.util.ArrayList;

public interface GameInterface {
    boolean isDead();

    boolean isWaiting();
    void step(ArrayList<BaseHero> armies, Score score);

    void upkeep();

    int getSide();

    State getState();

    Position getPosition();

    String getInfo();


}
