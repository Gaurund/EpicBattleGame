package com.epicbattle.game.units;


public class Mana {
    private final int maxMana;
    private int mana;

    private boolean depleted;
    public Mana(int mana) {
        this.mana = mana;
        this.maxMana = mana;
        this.depleted = false;
    }

    public int getMana(){
        return this.mana;
    }

    public void payMana(int cost){
        mana = mana - cost;
        if(mana <= 0) {
            mana = 0;
            depleted = true;
        }
    }

    public boolean isDepleted(){
        return depleted;
    }
    public void restoreMana(int newMana){
        mana = mana + newMana;
        depleted = false;
        if(mana >= maxMana){
            mana = maxMana;
        }

    }
}
