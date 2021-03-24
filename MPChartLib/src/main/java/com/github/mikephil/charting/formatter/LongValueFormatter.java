package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class LongValueFormatter extends ValueFormatter {

    private long reference;

    public LongValueFormatter(long reference) {
        this.reference = reference;
    }

    public String getFormattedValue(long value) {
        return String.valueOf(value);
    }

    public String getAxisLabel(long value, AxisBase axis) {
        return getFormattedValue(value);
    }

    @Override
    @Deprecated
    final public String getFormattedValue(float value, AxisBase axis) {
        long longValue = (long)value + reference;
        return this.getFormattedValue(longValue);
    }

    @Override
    @Deprecated
    final public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return super.getFormattedValue(value, entry, dataSetIndex, viewPortHandler);
    }

    @Override
    @Deprecated
    final public String getFormattedValue(float value) {
        long longValue = (long)value + reference;
        return this.getFormattedValue(longValue);
    }

    @Override
    @Deprecated
    final public String getAxisLabel(float value, AxisBase axis) {
        long longValue = (long)value + reference;
        return this.getAxisLabel(longValue, axis);
    }

    @Override
    @Deprecated
    final public String getBarLabel(BarEntry barEntry) {
        return super.getBarLabel(barEntry);
    }

    @Override
    @Deprecated
    final public String getBarStackedLabel(float value, BarEntry stackedEntry) {
        return super.getBarStackedLabel(value, stackedEntry);
    }

    @Override
    @Deprecated
    final public String getPointLabel(Entry entry) {
        return super.getPointLabel(entry);
    }

    @Override
    @Deprecated
    final public String getPieLabel(float value, PieEntry pieEntry) {
        return super.getPieLabel(value, pieEntry);
    }

    @Override
    @Deprecated
    final public String getRadarLabel(RadarEntry radarEntry) {
        return super.getRadarLabel(radarEntry);
    }

    @Override
    @Deprecated
    final public String getBubbleLabel(BubbleEntry bubbleEntry) {
        return super.getBubbleLabel(bubbleEntry);
    }

    @Override
    @Deprecated
    final public String getCandleLabel(CandleEntry candleEntry) {
        return super.getCandleLabel(candleEntry);
    }
}
