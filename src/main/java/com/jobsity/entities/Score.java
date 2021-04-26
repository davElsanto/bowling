package com.jobsity.entities;

public class Score {

    private int pinsValue1;
    private int pinsValue2;
    private boolean isStrike;
    private boolean isSpare;
    private boolean isFoul;
    private boolean isOpen;
    private int total;

    public Score() {
    }

    public Score(int pinsValue1, int pinsValue2, boolean isStrike, boolean isSpare, boolean isFoul, boolean isOpen, int total) {
        this.pinsValue1 = pinsValue1;
        this.pinsValue2 = pinsValue2;
        this.isStrike = isStrike;
        this.isSpare = isSpare;
        this.isFoul = isFoul;
        this.isOpen = isOpen;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isFoul() {
        return isFoul;
    }

    public void setFoul(boolean foul) {
        isFoul = foul;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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

    @Override
    public String toString() {
        return "Score{" +
                "pinsValue1=" + pinsValue1 +
                ", pinsValue2=" + pinsValue2 +
                ", isStrike=" + isStrike +
                ", isSpare=" + isSpare +
                ", isFoul=" + isFoul +
                ", isOpen=" + isOpen +
                ", total=" + total +
                '}';
    }
}
