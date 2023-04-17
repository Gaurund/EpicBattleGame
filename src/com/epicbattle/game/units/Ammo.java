package com.epicbattle.game.units;

public class Ammo {

    private int arrows;
    private final int maxArrows;

    public Ammo(int arrows) {
        this.arrows = arrows;
        this.maxArrows = arrows;
    }

    public int getArrows(){
        return arrows;
    }

    public boolean isNotEmpty(){
        return arrows > 0;
    }

    public boolean isNotFull(){
        return arrows != maxArrows;
    }
    public void addArrow(){
        if(isNotFull()) arrows += 1;
    }

    public void removeArrow(){
        if(isNotEmpty()) arrows -= 1;
    }

}
