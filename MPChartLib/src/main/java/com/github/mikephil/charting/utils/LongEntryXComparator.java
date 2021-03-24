package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.data.LongEntry;

import java.util.Comparator;

public class LongEntryXComparator implements Comparator<LongEntry> {

    @Override
    public int compare(LongEntry entry1, LongEntry entry2) {
        long diff = entry1.getX() - entry2.getX();

        if(diff == 0f) return 0;
        else {
            if(diff > 0f) return 1;
            else return -1;
        }
    }
}
