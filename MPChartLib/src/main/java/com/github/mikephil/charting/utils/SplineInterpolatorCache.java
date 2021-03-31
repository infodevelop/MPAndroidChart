package com.github.mikephil.charting.utils;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SplineInterpolatorCache {

    SplineInterpolator interpolator = new SplineInterpolator();
    PolynomialSplineFunction splineFunction;
    ILineDataSet cachedDataSet;

    public PolynomialSplineFunction getCachedSplineFunction(ILineDataSet dataSet) {

        if (cachedDataSet == null || splineFunction == null || !cachedDataSet.isEquals(dataSet) ) {
            double[] xArray = new double[dataSet.getEntryCount()];
            double[] yArray = new double[dataSet.getEntryCount()];
            ArrayList<Entry> entries = new ArrayList<>();

            for (int i = 0; i < dataSet.getEntryCount(); i++) {
                xArray[i] = dataSet.getEntryForIndex(i).getX();
                yArray[i] = dataSet.getEntryForIndex(i).getY();
                entries.add(dataSet.getEntryForIndex(i));
            }

            splineFunction = interpolator.interpolate(xArray, yArray);
            cachedDataSet = new LineDataSet(entries, dataSet.getLabel());
            Log.e("Cache", "갱신됐지롱");
        }
        return splineFunction;

    }

}
