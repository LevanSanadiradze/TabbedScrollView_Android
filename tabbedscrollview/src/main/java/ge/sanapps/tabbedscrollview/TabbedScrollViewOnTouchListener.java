package ge.sanapps.tabbedscrollview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by Levan Sanadiradze on 12/30/17.
 */

public abstract class TabbedScrollViewOnTouchListener implements OnTouchListener {

    private boolean wasDown = false;
    private float lastX = 0;
    private float lastY = 0;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                wasDown = true;
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if(wasDown)
                    onMoveStop();
                wasDown = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(wasDown) {
                    if(Math.abs(event.getX() - lastX) >= Math.abs(event.getY() - lastY))
                        onMoveSidewards(event.getX() - lastX);
                    else
                        onMoveUpDown(event.getY() - lastY);
                }
                wasDown = true;
                lastX = event.getX();
                lastY = event.getY();
                break;
        }

        return true;
    }

    public abstract void onMoveSidewards(float dX);

    public abstract void onMoveUpDown(float dY);

    public abstract void onMoveStop();
}
