package com.github.mikephil.charting.test;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.helper.PagerGestureHelper;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class ScrollPagerActivityTest {

    @Mock
    LineChart chart;

    PagerGestureHelper helper;

    LineDataSet dataSet;
    LineData data;

    private final int GESTURE_SENSITIVITY = 1000;
    private final int FAST_GESTURE_RATIO = 10;

    @Before
    public void setUp() {
        chart = Mockito.mock(LineChart.class);
        helper = new PagerGestureHelper(GESTURE_SENSITIVITY, FAST_GESTURE_RATIO);

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            entries.add(new Entry(i, (float) (Math.random() * 10)));
        }

        dataSet = new LineDataSet(entries, "label");
        data = new LineData(dataSet);

        Mockito.when(chart.getData()).thenReturn(data);
        Mockito.when(chart.getVisibleXRange()).thenReturn(7f);
    }

    @Test
    public void pagerGestureHelper_flingRightGesture_moveOnePage() {
        helper.init(chart);

        int beforePage = helper.getVisiblePageNo();

        helper.onChartFling(null, null, -4000, 0);

        assertEquals(beforePage + 1, helper.getVisiblePageNo());
    }

    @Test
    public void pagerGestureHelper_flingFastRightGesture_moveThreePage() {
        helper.init(chart);

        int beforePage = helper.getVisiblePageNo();

        helper.onChartFling(null,  null, -(GESTURE_SENSITIVITY * FAST_GESTURE_RATIO + 1000), 0);

        assertEquals(beforePage + 3, helper.getVisiblePageNo());
    }

    @Test
    public void pagerGestureHelper_flingLeftGesture_moveOnePage() {
        helper.init(chart);

        int beforePage = helper.getVisiblePageNo();

        helper.onChartFling(null, null, 4000, 0);

        assertEquals(Math.max(beforePage - 1, 1), helper.getVisiblePageNo());
    }

    @Test
    public void pagerGestureHelper_flingFastLeftGesture_moveThreePage() {
        helper.init(chart);

        int beforePage = helper.getVisiblePageNo();

        helper.onChartFling(null, null, GESTURE_SENSITIVITY * FAST_GESTURE_RATIO + 1000, 0);

        assertEquals(Math.max(beforePage - 3, 1), helper. getVisiblePageNo());
    }

    @Test
    public void pagerGestureHelper_flingFastRightGestureAndFlingLeftGesture_moveTwoPage() {
        helper.init(chart);

        int beforePage = helper.getVisiblePageNo();

        // Fast Right Gesture
        helper.onChartFling(null,  null, -(GESTURE_SENSITIVITY * FAST_GESTURE_RATIO + 1000), 0);

        // Left Gesture
        helper.onChartFling(null, null, 4000, 0);

        assertEquals(beforePage + 2, helper.getVisiblePageNo());
    }

    @Test
    public void pagerGestureHelper_flingFastRightGestureDoubleAndFlingFastLeftGesture_moveThreePage() {
        helper.init(chart);

        int beforePage = helper.getVisiblePageNo();

        // Fast Right Gesture
        helper.onChartFling(null,  null, -(GESTURE_SENSITIVITY * FAST_GESTURE_RATIO + 1000), 0);

        // Fast Right Gesture
        helper.onChartFling(null,  null, -(GESTURE_SENSITIVITY * FAST_GESTURE_RATIO + 1000), 0);

        // Fast Left Gesture
        helper.onChartFling(null, null, GESTURE_SENSITIVITY * FAST_GESTURE_RATIO + 1000, 0);

        assertEquals(beforePage + 3, helper.getVisiblePageNo());

    }

}
