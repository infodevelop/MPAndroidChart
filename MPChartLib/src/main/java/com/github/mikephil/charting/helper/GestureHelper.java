package com.github.mikephil.charting.helper;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.listener.OnChartGestureListener;

public abstract class GestureHelper<T extends Chart> implements OnChartGestureListener {

    protected T mChart;

    public void init(T mChart) {
        this.mChart = mChart;
    }

}
