package com.github.mikephil.charting.helper;

import android.view.MotionEvent;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;

public class PagerGestureHelper extends GestureHelper<BarLineChartBase> {

    /**
     * 스크롤 제스처 터치 감도
     */
    private int GESTURE_SENSITIVITY;

    /**
     * 빠른 스크롤 제스처 감도 비율
     * ex. 10 => 기존 스크롤에 비해 10배 빠르게 제스쳐했을 경우
     */
    private int FAST_GESTURE_RATIO;

    private Float mVisiblePointXValue;
    private Float mVisiblePointYValue;

    private Float mPreviousPointXValue;
    private Float mPreviousPointYValue;

    private Float mNextPointXValue;
    private Float mNextPointYValue;

    private int mPageNo = 1;

    private IDataSet mReferenceDataSet;

    private ChartGesture mChartGesture;

    enum ChartGesture {
        START,
        SCROLL,
        FLING,
        END
    }

    public PagerGestureHelper() {
        this.GESTURE_SENSITIVITY = 1000;
        this.FAST_GESTURE_RATIO = 10;
    }
    
    public PagerGestureHelper(int gestureSensitivity, int fastGestureRatio) {
        this.GESTURE_SENSITIVITY = gestureSensitivity;
        this.FAST_GESTURE_RATIO = fastGestureRatio;
    }

    public void setGestureSensitivity(int gestureSensitivity) {
        this.GESTURE_SENSITIVITY = gestureSensitivity;
    }

    public void setFastGestureRatio(int fastGestureRatio) {
        this.FAST_GESTURE_RATIO = fastGestureRatio;
    }

    @Override
    public void init(BarLineChartBase mChart) {
        super.init(mChart);

        this.mChart.setDoubleTapToZoomEnabled(false);
        this.mChart.setDragDecelerationEnabled(false);

        IDataSet dataSet;
        Entry entry, nextEntry;

        for(int i = 0; i < mChart.getData().getDataSetCount(); i++) {
            dataSet = mChart.getData().getDataSetByIndex(i);
            entry = dataSet.getEntryForIndex(0);
            nextEntry = dataSet.getEntryForIndex((int) (mChart.getVisibleXRange() * mPageNo));

            if(mVisiblePointXValue == null || mVisiblePointYValue == null || mVisiblePointXValue > entry.getX()) {
                mVisiblePointXValue = mPreviousPointXValue = entry.getX();
                mVisiblePointYValue = mPreviousPointYValue = entry.getY();

                mNextPointXValue = nextEntry.getX();
                mNextPointYValue = nextEntry.getY();

                mReferenceDataSet = dataSet;
            }
        }
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        mChartGesture = ChartGesture.START;
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        if(mChartGesture != ChartGesture.FLING) {
            moveToFirstVisibleValue();
        }

        mChartGesture = ChartGesture.END;
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        if(velocityX > GESTURE_SENSITIVITY * FAST_GESTURE_RATIO) {
            mChartGesture = ChartGesture.FLING;
            moveToPreviousPage(3, YAxis.AxisDependency.LEFT, 500);
        }
        else if(velocityX < (-GESTURE_SENSITIVITY) * FAST_GESTURE_RATIO) {
            mChartGesture = ChartGesture.FLING;
            moveToNextPage(3, YAxis.AxisDependency.LEFT, 500);
        }
        else if(velocityX > GESTURE_SENSITIVITY) {
            mChartGesture = ChartGesture.FLING;
            moveToPreviousPage(1, YAxis.AxisDependency.LEFT, 500);
        }
        else if(velocityX < -GESTURE_SENSITIVITY) {
            mChartGesture = ChartGesture.FLING;
            moveToNextPage(1, YAxis.AxisDependency.LEFT, 500);
        }
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        mChartGesture = ChartGesture.SCROLL;
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
    }

    private void moveToFirstVisibleValue() {
        IDataSet dataSet;
        Entry firstVisibleEntry = null, temp;
        MPPointD calPoint;
        Transformer trs = mChart.getTransformer(YAxis.AxisDependency.LEFT);

        for(int i = 0; i < mChart.getData().getDataSetCount(); i++) {

            dataSet = mChart.getData().getDataSetByIndex(i);

            for(int j = 0; j < dataSet.getEntryCount(); j++) {

                temp = dataSet.getEntryForIndex(j);
                calPoint = trs.getPixelForValues(temp.getX(), temp.getY());

                if(calPoint.x > 0) {

                    if(firstVisibleEntry != null) {
                        if(temp.getX() < firstVisibleEntry.getX()) {
                            firstVisibleEntry = temp;
                            j = dataSet.getEntryCount();
                        }
                    }
                    else {
                        firstVisibleEntry = temp;
                        j = dataSet.getEntryCount();
                    }

                }

            }

        }

        assert firstVisibleEntry != null;
        mChart.moveViewToAnimated(firstVisibleEntry.getX(), firstVisibleEntry.getY(), YAxis.AxisDependency.LEFT, 200);
        mPageNo = getPageNoByXValue(firstVisibleEntry.getX());
        changePointValueByPageNo(mPageNo);
    }

    private void moveToPreviousPage(int movePageCnt, YAxis.AxisDependency axis, long duration) {
        if(mPageNo > 1) {
            mPageNo -= movePageCnt;

            mPageNo = Math.max(1, mPageNo);

            changePointValueByPageNo(mPageNo);

            moveToVisiblePage(axis, duration);
        }
    }

    private void moveToNextPage(int movePageCnt, YAxis.AxisDependency axis, long duration) {
        if(!mNextPointXValue.equals(mVisiblePointXValue)) {
            mPageNo += movePageCnt;

            mPageNo = (int) Math.min(Math.ceil(mReferenceDataSet.getEntryCount() / mChart.getVisibleXRange()), mPageNo);

            changePointValueByPageNo(mPageNo);

            moveToVisiblePage(axis, duration);
        }
    }

    private void moveToVisiblePage(YAxis.AxisDependency axis, long duration) {
        mChart.moveViewToAnimated(mVisiblePointXValue, mVisiblePointYValue, axis, duration);
    }

    private int getPageNoByXValue(float xValue) {
        return (int)(Math.ceil(xValue / mChart.getVisibleXRange()));
    }
    
    private void changePointValueByPageNo(int pageNo) {
        Entry previousEntry = mReferenceDataSet.getEntryForIndex(Math.max((int)((pageNo - 2) * mChart.getVisibleXRange()), 0));
        Entry visibleEntry = mReferenceDataSet.getEntryForIndex(Math.max((int)((pageNo - 1) * mChart.getVisibleXRange()), 0));
        Entry nextEntry = mReferenceDataSet.getEntryForIndex(Math.max((int)(pageNo * mChart.getVisibleXRange()) % mReferenceDataSet.getEntryCount(), 0));

        mPreviousPointXValue = previousEntry.getX();
        mPreviousPointYValue = previousEntry.getY();

        mVisiblePointXValue = visibleEntry.getX();
        mVisiblePointYValue = visibleEntry.getY();

        mNextPointXValue = nextEntry.getX();
        mNextPointYValue = nextEntry.getY();
    }

}
