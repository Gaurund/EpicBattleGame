package com.epicbattle.game.units;


public class State {
    private static final String[] states = new String[]{"\u2670 мёртв", "ждёт", "занят", "скрыт", "под магической защитой"};
    private int stateID;

    public State() {
        this.stateID = 1;
    }

    public int getStateID() {
        return stateID;
    }

    public String getStateName() {
        return states[stateID];
    }

    private void setState(int state) {
        stateID = state;
    }

    public void die() {
        setState(0);
    }
    public void setBusy() {
        setState(2);
    }

    public void setWaiting() {
        setState(1);
    }


    public boolean isDead() {
        return this.getStateID() == 0;
    }


    public boolean isWaiting() {
        return stateID == 1;
    }
    public boolean isBusy() {
        return stateID == 2;
    }

    public boolean isHidden() {
        return stateID == 3;
    }

    public boolean isShielded() {
        return stateID == 4;
    }
}
