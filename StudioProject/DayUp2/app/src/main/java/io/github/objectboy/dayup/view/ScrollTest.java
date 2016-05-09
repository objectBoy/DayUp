package io.github.objectboy.dayup.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by cun on 2016/5/6.
 * description: this is a test about Class:Scroller
 */
public class ScrollTest extends View {

    private Scroller mScroller;
    private int lastX;
    private int lastY;

    public ScrollTest(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public ScrollTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public ScrollTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScrollTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.e("TEst", "onTouchEvent: lastX" + x);
                Log.e("TEst", "onTouchEvent: lastY" + y);
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                Log.e("TEst", "onTouchEvent: offsetX" + offsetX);
                Log.e("TEst", "onTouchEvent: offsetY" + offsetY);
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(), -viewGroup.getScrollX(), -viewGroup.getScrollY());
                Log.e("TEst", "onTouchEvent: Curr-Up" + mScroller.getCurrX() + "--------" + mScroller.getCurrY());
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.e("TEst", "onTouchEvent: Curr" + mScroller.getCurrX() + "--------" + mScroller.getCurrY());
            invalidate();
        }
    }
}
