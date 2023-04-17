package com.epicbattle.game.units;


import java.util.ArrayList;

public abstract class Healer extends BaseHero {
    Mana mana;
    float restoreHealth;

    public Healer(String className, String name, int side, int x, int y, float health, float range, int attack, int defense, int[] damage, int priority, float restoreHealth) {
        super(className, name, side, x, y, health, range, attack, defense, damage, priority);
        this.mana = new Mana(100);
        this.restoreHealth = restoreHealth;
    }

    public void step(ArrayList<BaseHero> armies, Score score) {
        if (isDead()) {
            return;
        }
        if(!this.mana.isDepleted())
        {
            ArrayList<BaseHero> wounded = findWounded(armies);
            if (wounded.size() > 0) {
                BaseHero mostWounded = findMostWounded(wounded);
                mostWounded.health.heal(restoreHealth);
                mana.payMana(10);
            } else {
                BaseHero nearestEnemy = nearestEnemy(armies);
                if (nearestEnemy != null) {
                    if (checkRange(nearestEnemy)) {
                        fire(nearestEnemy, score);
                    } else {
                        this.getPosition().move(nearestEnemy, armies);
                    }
                }
            }
        }

    }

    protected ArrayList<BaseHero> findWounded(ArrayList<BaseHero> armies) {
        ArrayList<BaseHero> wounded = new ArrayList<>();
        for (BaseHero e : armies) {
            if (e.getSide() == this.getSide() && e.isHurt() && !e.isDead()) {
                wounded.add(e);
            }
        }
        return wounded;
    }

    protected BaseHero findMostWounded(ArrayList<BaseHero> wounded) {
        BaseHero mostWounded = wounded.get(0);
        for (BaseHero e : wounded) {
            if (mostWounded.woundValue() < e.woundValue()) mostWounded = e;
        }
        return mostWounded;
    }

    protected void fire(BaseHero enemy, Score score) {
        mana.payMana(10);
        this.inflictDamage(enemy, score);
    }
    @Override
    public String toString() {
        String str = super.toString();
        if (!isDead()) str += "[\u26B6: " + mana.getMana() + "]";
        return str;
    }
}
