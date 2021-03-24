package com.github.mikephil.charting.data;


import com.github.mikephil.charting.utils.LongEntryXComparator;

import java.util.ArrayList;
import java.util.Collections;

public class LongEntryList {

    private ArrayList<LongEntry> longEntries;
    private long referenceXValue;
    private long referenceYValue;

    public LongEntryList(ArrayList<LongEntry> entries) {
        this.longEntries = entries;
        sortByXValue(this.longEntries);
        findReferenceXValue(this.longEntries);
        findReferenceYValue(this.longEntries);
    }

    public void addLongEntry(LongEntry entry) {
        this.longEntries.add(entry);
        sortByXValue(this.longEntries);
    }

    public void removeLongEntry(LongEntry entry) {
        this.longEntries.remove(entry);
    }

    public ArrayList<Entry> getEntry() {

        ArrayList<Entry> list = new ArrayList();

        float x = 0;
        float y = 0;

        for(int i = 0; i < longEntries.size() ; i++) {
            LongEntry data = longEntries.get(i);

            x = (float)(data.getX() - referenceXValue);
            y = (float)(data.getY() - referenceYValue);

            if(data.getData() != null && data.getIcon() != null) {
                list.add(new Entry(x, y, data.getIcon(), data.getData()));
            }
            else if(data.getData() != null) {
                list.add(new Entry(x, y, data.getData()));
            }
            else if(data.getIcon() != null) {
                list.add(new Entry(x, y, data.getIcon()));
            }
            else {
                list.add(new Entry(x, y));
            }
        }

        return list;

    }

    public long getReferenceXValue() {
        return referenceXValue;
    }

    public long getReferenceYValue() {
        return referenceYValue;
    }

    private void findReferenceXValue(ArrayList<LongEntry> entries) {
        referenceXValue = entries.get(0).getX();
    }

    private void findReferenceYValue(ArrayList<LongEntry> entries) {
        referenceYValue = entries.get(0).getY();
    }

    private void sortByXValue(ArrayList<LongEntry> entries) {
        Collections.sort(this.longEntries, new LongEntryXComparator());
    }

}
