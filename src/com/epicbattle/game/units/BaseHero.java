package com.epicbattle.game.units;


import java.util.ArrayList;
import java.util.Random;

public abstract class BaseHero implements GameInterface {
    public String name;
    protected String className;
    protected Side side;
    protected int priority;
    protected Health health;
    protected double range;
    protected int attack;
    protected int defense;
    protected int[] damage;
    protected Position position;
    protected State state;

    public BaseHero(String className, String name, int side, int x, int y, float health, float range, int attack, int defense, int[] damage, int priority) {
        this.className = className;
        this.name = name;
        this.side = new Side(side);
        this.position = new Position(x, y);
        this.priority = randomizePriority(priority);
        this.health = new Health(health);
        this.range = range;
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.state = new State();
    }

    @Override
    public String toString() {
        int x = this.position.getX();
        int y = this.position.getY();
        return className + " " + name + " [ ♥:" + this.getHealth() + " \u2694:" +this.attack + " \u26CA:" + this.defense + " ][" + state.getStateName() + "]";
    }

    private int randomizePriority(int priority) {
        return new Random().nextInt(15) + priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int getSide() {
        return side.getSideID();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public int getX(){
        return position.getX();
    }
    public int getY(){
        return position.getY();
    }
    @Override
    public State getState() {
        return state;
    }


    public Float getHealth() {
        return health.getHealth();
    }

    public Float woundValue(){
        return  health.woundValue();
    }


    public Ammo getAmmo() {
        return null;
    }

    public int getDefense() {
        return defense;
    }

    public void getDamage(float damage){
        this.health.getDamage(damage);
    }
    @Override
    public String getInfo() {
        return className;
    }

    public void inflictDamage(BaseHero enemy, Score score) {
        float damageValue = (attack - enemy.getDefense()) + (new Random().nextInt(damage[1] - damage[0]) + damage[0]);
          damageValue = damageValue / 10;
        enemy.getDamage(damageValue);
        if (enemy.getHealth() <= 0) {
            enemy.die();
            score.decrementScore(enemy.getSide());
        }
    }

    public void upkeep() {
        if (isBusy()) {
            state.setWaiting();
        }
    }

    protected boolean isEnemy(BaseHero hero) {
        return hero.getSide() != this.getSide();
    }
    public boolean isDead() {
        return state.isDead();
    }
    private boolean isBusy() {
        return state.isBusy();
    }
    public boolean isWaiting() {
        return state.isWaiting();
    }
    public boolean isHidden() {
        return state.isHidden();
    }

    public boolean isShielded() {
        return state.isShielded();
    }

    public void die() {
        state.die();
    }

    public BaseHero nearestEnemy(ArrayList<BaseHero> armies) {
        float minDistance = 100;
        BaseHero enemy = null;
        for (BaseHero e : armies) {
            if (e.isDead() || e.isHidden() || e.isShielded()) continue;
            if (isEnemy(e)) {
                float estimatedDistance = position.getDistance(e);
                if (minDistance > estimatedDistance) {
                    minDistance = estimatedDistance;
                    enemy = e;
                }
            }
        }

        return enemy;
    }

    public boolean checkRange(BaseHero enemy) {
        float distance = position.getDistance(enemy);
        return distance < range;
    }

    protected boolean isHurt() {
        return health.isHurt();
    }
}
