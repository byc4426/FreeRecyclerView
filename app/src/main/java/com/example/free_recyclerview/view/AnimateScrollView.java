package com.example.free_recyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.widget.HorizontalScrollView;

/**
 * myt.
 */
public class AnimateScrollView extends HorizontalScrollView {
    private FreeRecyclerView myRecyclerView;

    private VelocityTracker velocityTracker = null;
    public AnimateScrollView(Context context) {
        super(context);
        setOverScrollMode(OVER_SCROLL_NEVER);

    }

    public AnimateScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    int downX;
    private int mLastXIntercept,mLastYIntercept;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        getMyRecyclerView().scrollTo(l);
    }
    private FreeRecyclerView getMyRecyclerView(){
        if (myRecyclerView == null){
            myRecyclerView = (FreeRecyclerView)getTag();
        }
        return myRecyclerView;
    }

}
