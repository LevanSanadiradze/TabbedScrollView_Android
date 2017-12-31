package ge.sanapps.tabbedscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by Levan Sanadiradze on 12/30/17.
 */

public class TabbedHorizontalScrollView extends HorizontalScrollView {

    private int stepSize = 180;
    private double scrollStrength = 5.0;

    private double remain = 0;

    public TabbedHorizontalScrollView(Context context) {
        super(context);
        createAndSetOnTouchListener();
    }

    public TabbedHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createAndSetOnTouchListener();
    }

    public TabbedHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createAndSetOnTouchListener();
    }

    public int getStepSize() {
        return stepSize;
    }

    public double getScrollStrength() {
        return scrollStrength;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public void setScrollStrength(double scrollStrength) {
        this.scrollStrength = scrollStrength;
    }

    private void createAndSetOnTouchListener()
    {
        TabbedScrollViewOnTouchListener HSVL = new TabbedScrollViewOnTouchListener() {
            @Override
            public void onMoveSidewards(float dX) {
                TabbedHorizontalScrollView.this.onMoveSidewards(dX);
            }

            @Override
            public void onMoveUpDown(float dY) {

            }

            @Override
            public void onMoveStop() {
                TabbedHorizontalScrollView.this.onMoveStop();
            }
        };

        setOnTouchListener(HSVL);
    }

    private void onMoveSidewards(float dX)
    {
        remain += (-dX * scrollStrength / getWidth()) * stepSize;
        if(Math.abs(remain) >= 1.0) {
            smoothScrollBy((int) Math.floor(remain), 0);
            remain -= Math.floor(remain);
        }
    }

    private void onMoveStop()
    {
        int newX = (int)Math.round((double) getScrollX() / (double)stepSize) * stepSize;
        smoothScrollTo(newX, 0);
    }

    public void snap()
    {
        onMoveStop();
    }
}
