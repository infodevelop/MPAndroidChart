package com.github.mikephil.charting.utils;

public class LinearFunctionGraph {

    private double slope = 0.0;
    private double yIntercept = 0.0;

    public void set(double x1, double y1, double x2, double y2) {
        double xIncrease = x2 - x1;
        double yIncrease = y2 - y1;

        if(xIncrease == 0 || yIncrease == 0) {
            slope = 0;
        }
        else {
            slope = yIncrease / xIncrease;
        }

        yIntercept =  -slope * x1 + y1;
    }

    public double getY(double x) {
        return slope * x + yIntercept;
    }

}
