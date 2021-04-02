package com.github.mikephil.charting.test;

import com.github.mikephil.charting.data.LongEntry;
import com.github.mikephil.charting.data.LongEntryList;
import com.github.mikephil.charting.formatter.LongValueFormatter;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DateLabelSupportTest {

    ArrayList<LongEntry> list = new ArrayList<>();
    LongEntryList longEntryList;
    LongValueFormatter formatter;

    @Before
    public void setUp() {
        for(int i = 1 ; i <= 15 ; i++) {
            list.add(new LongEntry(Date.UTC(2021, 4, i, 12, 0, 0), (long) (Math.random() * 10)));
        }

        longEntryList = new LongEntryList(list);

        formatter = new LongValueFormatter(longEntryList.getReferenceXValue());
    }

    @Test
    public void test() {
        assertEquals(String.valueOf(list.get(0).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(0).getX()));

        assertEquals(String.valueOf(list.get(1).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(1).getX()));

        assertEquals(String.valueOf(list.get(2).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(2).getX()));

        assertEquals(String.valueOf(list.get(3).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(3).getX()));

        assertEquals(String.valueOf(list.get(4).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(4).getX()));

        assertEquals(String.valueOf(list.get(5).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(5).getX()));

        assertEquals(String.valueOf(list.get(6).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(6).getX()));

        assertEquals(String.valueOf(list.get(7).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(7).getX()));

        assertEquals(String.valueOf(list.get(8).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(8).getX()));

        assertEquals(String.valueOf(list.get(9).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(9).getX()));

        assertEquals(String.valueOf(list.get(10).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(10).getX()));

        assertEquals(String.valueOf(list.get(11).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(11).getX()));

        assertEquals(String.valueOf(list.get(12).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(12).getX()));

        assertEquals(String.valueOf(list.get(13).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(13).getX()));

        assertEquals(String.valueOf(list.get(14).getX()), formatter.getFormattedValue(longEntryList.getEntry().get(14).getX()));
    }
}
