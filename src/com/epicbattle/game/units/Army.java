package com.epicbattle.game.units;

import static units.NameGenerator.NameGenerator.getName;

import java.util.ArrayList;
import java.util.Random;

public class Army {
    private  ArrayList<BaseHero> whites;
    private  ArrayList<BaseHero> blacks;
    private  ArrayList<BaseHero> armies;

    public Army() {
        this.whites = new ArrayList<>();
        this.blacks = new ArrayList<>();
        this.armies = new ArrayList<>();

        createArmies();
        sortArmies();

    }

    private static void fillArmy(ArrayList<BaseHero> list, int shift) {
        Random rnd = new Random();
        for (int x = 1; x < 11; x++) {
            switch (rnd.nextInt(4) + shift) {
                case 1:
                    list.add(new Archer(getName(), x, shift));
                    break;
                case 2:
                    list.add(new Monk(getName(), x, shift));
                    break;
                case 3:
                    list.add(new Rogue(getName(), x, shift));
                    break;
                case 4:
                    list.add(new Peasant(getName(), 0, x, shift));
                    break;
                case 10:
                    list.add(new Crossbowman(getName(), x, shift));
                    break;
                case 11:
                    list.add(new Wizard(getName(), x, shift));
                    break;
                case 12:
                    list.add(new Lancer(getName(), x, shift));
                    break;
                default:
                    list.add(new Peasant(getName(), 1, x, shift));
            }
        }
    }

    private void createArmies() {
        fillArmy(whites, 1);
        fillArmy(blacks, 10);
        armies.addAll(whites);
        armies.addAll(blacks);
    }

    private void sortArmies() {
//        armies.sort(Comparator.comparingInt(BaseHero::getPriority));
    }

    public ArrayList<BaseHero> whites() {
        return whites;
    }

    public ArrayList<BaseHero> blacks() {
        return blacks;
    }

    public ArrayList<BaseHero> all() {
        return armies;
    }
}
