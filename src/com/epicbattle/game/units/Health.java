package com.epicbattle.game.units;


public class Health {

    private final float maxHealth;
    private float health;


    public Health(float health) {
        this.health = health;
        this.maxHealth = health;
    }

    public boolean isHurt() {
        return health != maxHealth;
    }

    public void heal(float healing) {
        health = health + healing;
        if (health > maxHealth) health = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public void getDamage(float damage) {
        health = health - damage;
    }

    public float woundValue() {
        return maxHealth - health;
    }

}
