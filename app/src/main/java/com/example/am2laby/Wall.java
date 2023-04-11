package com.example.am2laby;

public class Wall {
    private float startx;
    private float starty;
    private float endx;
    private float endy;


    public Wall() {
    }

    public Wall(float startx, float starty, float endx, float endy) {
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
    }

    public float getStartx() {
        return startx;
    }

    public void setStartx(float startx) {
        this.startx = startx;
    }

    public float getStarty() {
        return starty;
    }

    public void setStarty(float starty) {
        this.starty = starty;
    }

    public float getEndx() {
        return endx;
    }

    public void setEndx(float endx) {
        this.endx = endx;
    }

    public float getEndy() {
        return endy;
    }

    public void setEndy(float endy) {
        this.endy = endy;
    }
}


