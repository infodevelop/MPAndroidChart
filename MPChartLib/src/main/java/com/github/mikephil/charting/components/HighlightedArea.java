package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;

public class HighlightedArea extends ComponentBase {

    private float mStartValue = 0f;

    private float mEndValue = 0f;

    private int mBackgroundColor = 0;

    private int mLineColor = 0;

    private float mLineWidth = 0f;

    private DashPathEffect mDashPathEffect = null;

    public HighlightedArea(float startValue, float endValue) {

        if(startValue >= endValue) {
            throw new IllegalArgumentException("The start value cannot be greater than the end value.");
        }

        this.mStartValue = startValue;
        this.mEndValue = endValue;
    }

    public void setStartValue(float mStartValue) {

        if(mStartValue >= this.mEndValue) {
            throw new IllegalArgumentException("The start value cannot be greater than the end value.");
        }

        this.mStartValue = mStartValue;
    }

    public float getStartValue() {
        return mStartValue;
    }

    public void setEndValue(float mEndValue) {

        if(this.mStartValue >= mEndValue) {
            throw new IllegalArgumentException("The end value cannot be less than the start value.");
        }

        this.mEndValue = mEndValue;
    }

    public float getEndValue() {
        return mEndValue;
    }

    public void setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setLineColor(int mLineColor) {
        this.mLineColor = mLineColor;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public void setDashPathEffect(DashPathEffect mDashPathEffect) {
        this.mDashPathEffect = mDashPathEffect;
    }

    public DashPathEffect getDashPathEffect() {
        return mDashPathEffect;
    }

    public void setLineWidth(float mLineWidth) {
        this.mLineWidth = mLineWidth;
    }

    public float getLineWidth() {
        return mLineWidth;
    }
}
