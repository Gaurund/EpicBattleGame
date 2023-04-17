package com.epicbattle.game.units;


import java.util.ArrayList;
import java.util.Random;

public abstract class Ranged extends BaseHero {
    int accuracy;
    private final Ammo ammo;

    public Ranged(String className, String name, int side, int x, int y, float health, float range, int attack, int defense, int[] damage, int priority, int arrows, int accuracy) {
        super(className, name, side, x, y, health, range, attack, defense, damage, priority);
        this.ammo = new Ammo(arrows);
        this.accuracy = accuracy;
    }

    protected void shoot(BaseHero enemy, Score score) {
        Random chance = new Random();
        ammo.removeArrow();
        if (chance.nextInt(101) < accuracy) {
            this.inflictDamage(enemy, score);
        }

    }


    protected boolean isNotEmpty() {
        return ammo.isNotEmpty();
    }


    @Override
    public void step(ArrayList<BaseHero> armies, Score score) {
        if (isDead()) {
            return;
        }

        if (isNotEmpty()) {
            BaseHero nearestEnemy = nearestEnemy(armies);
            if (nearestEnemy != null) {
                if (checkRange(nearestEnemy)){
                    shoot(nearestEnemy, score);
                } else {
                    this.getPosition().move(nearestEnemy, armies);
                }
            }
        }
        System.out.println();

    }

    @Override
    public String toString() {
        String str = super.toString();
        if (!isDead()) str += "[\u27B6: " + ammo.getArrows() + "]";
        return str;
    }

    @Override
    public Ammo getAmmo() {
        return ammo;
    }

}
