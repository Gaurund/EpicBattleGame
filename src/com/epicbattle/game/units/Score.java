package com.epicbattle.game.units;


public class Score {
    private final int[] score = new int[2];

    private int counter;
    public Score() {
        this.score[0] = 10;
        this.score[1] = 10;
        this.counter = 0;
    }
    private void incrementCounter(){
        counter = counter + 1;
    }
    private void resetCounter(){
        counter = 0;
    }

    private boolean checkCounter(){
        return counter>20;
    }
    public void decrementScore(int sideID) {
        score[sideID] -= 1;
        resetCounter();
    }

    public Integer checkScore() {
        if (score[0] == 0) {
            return 1;
        }
        if (score[1] == 0) {
            return 0;
        }
        incrementCounter();
        if (checkCounter()){
            return 3;
        }
        return null;
    }

//    @Override
//    public String toString() {
//        return "\nУ Синих в живых: " + score[0] + "\nУ Зелёных в живых: " + score[1] ;
//    }
}
