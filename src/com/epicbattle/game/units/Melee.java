package com.epicbattle.game.units;


import java.util.ArrayList;

public abstract class Melee extends BaseHero {
    int rage;
    int maxRage;

    public Melee(String className, String name, int side, int x, int y, float health, float range, int attack, int defense, int[] damage, int priority, int rage, int maxRage) {
        super(className, name, side, x, y, health, range, attack, defense, damage, priority);
        this.rage = rage;
        this.maxRage = maxRage;
    }

    public void hit(BaseHero enemy, Score score) {
        this.inflictDamage(enemy, score);
    }

    public void step(ArrayList<BaseHero> armies, Score score) {
        if (isDead()) {
            return;
        }
        BaseHero nearestEnemy = nearestEnemy(armies);
//        System.out.println(this + " >>> Цель: " + nearestEnemy);

        if (nearestEnemy != null) {
            if (checkRange(nearestEnemy)) {
                hit(nearestEnemy, score);
            } else {
//                System.out.println(this + " ===> " + nearestEnemy);
                this.getPosition().move(nearestEnemy, armies);
            }
        }
//        System.out.println();
    }


}
