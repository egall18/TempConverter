package edu.csumb.gall3079.tempconverter;

public class Temp {
    double f;
    double c;
    public Temp(double f, double c) {
        this.f = f;
        this.c = c;
    }


    @Override
    public String toString(){  return c + "\u00B0C = " + f + "\u00B0F"; }
}
