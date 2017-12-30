package ge.sanapps.tabbedscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by root on 12/30/17.
 */

public class TabbedVerticalScrollView extends ScrollView {

    private int stepSize = 180;
    private double scrollStrength = 3.0;

    private double remain = 0;

    public TabbedVerticalScrollView(Context context) {
        super(context);
        createAndSetOnTouchListener();
    }

    public TabbedVerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createAndSetOnTouchListener();
    }

    public TabbedVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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

            }

            @Override
            public void onMoveUpDown(float dY) {
                TabbedVerticalScrollView.this.onMoveUpDown(dY);
            }

            @Override
            public void onMoveStop() {
                TabbedVerticalScrollView.this.onMoveStop();
            }
        };

        setOnTouchListener(HSVL);
    }

    private void onMoveUpDown(float dY)
    {
        remain += (-dY * scrollStrength / getHeight()) * stepSize;
        if(Math.abs(remain) >= 1.0) {
            smoothScrollBy(0, (int) Math.floor(remain));
            remain -= Math.floor(remain);
        }
    }

    private void onMoveStop()
    {
        int newY = (int)Math.round((double) getScrollY() / (double)stepSize) * stepSize;
        smoothScrollTo(0, newY);
    }
}
