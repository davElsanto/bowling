package com.jobsity.entities;

public class Score {

    private int pinsValue1;
    private int pinsValue2;
    private boolean isStrike;
    private boolean isSpare;

    public Score() {
    }

    public Score(int pinsValue1, int pinsValue2, boolean isStrike, boolean isSpare) {
        this.pinsValue1 = pinsValue1;
        this.pinsValue2 = pinsValue2;
        this.isStrike = isStrike;
        this.isSpare = isSpare;
    }

    public int getPinsValue1() {
        return pinsValue1;
    }

    public void setPinsValue1(int pinsValue1) {
        this.pinsValue1 = pinsValue1;
    }

    public int getPinsValue2() {
        return pinsValue2;
    }

    public void setPinsValue2(int pinsValue2) {
        this.pinsValue2 = pinsValue2;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }
}
