package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;

public class LongEntry {

    private long longX = 0;
    private long longY = 0;
    private Drawable icon;
    private Object data;

    public LongEntry(long x, long y) {
        this.longX = x;
        this.longY = y;
    }

    public LongEntry(long x, long y, Object data) {
        this.longX = x;
        this.longY = y;
        this.data = data;
    }

    public LongEntry(long x, long y, Drawable icon) {
        this.longX = x;
        this.longY = y;
        this.icon = icon;
    }

    public long getX() { return longX; }
    public long getY() { return longY; }
    public Drawable getIcon() { return icon; }
    public Object getData() { return data; }

}
