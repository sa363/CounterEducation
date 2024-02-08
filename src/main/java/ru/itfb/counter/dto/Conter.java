package ru.itfb.counter.dto;


public class Conter {
    private int count;

    public Conter() {
        this.count = 1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void intcrementCount() {
        this.count++;
    }
}
