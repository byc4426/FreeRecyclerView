package com.example.free_recyclerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.free_recyclerview.R;
import com.example.free_recyclerview.adapter.HeaderWrapper;


/**
 * Created by Administrator on 2017/8/7.
 */

public class FreeRecyclerView extends RecyclerView {

    private int firstVisibleItemPosition = 0;
    private int lastVisibleItemPosition = 0;
    private int offset = 0;
    private HeaderWrapper adapter;

    private View mCurrentHeader;
    private float mHeaderOffset;

    public FreeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addScrollListener();
    }

    public FreeRecyclerView(Context context) {
        super(context);

        addScrollListener();
    }

    @Override
    public void addOnScrollListener(OnScrollListener listener) {
        super.addOnScrollListener(listener);
    }

    private void addScrollListener(){
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollBy(offset);

                mCurrentHeader = getHeaderView(firstVisibleItemPosition,mCurrentHeader);

            }
        });
    }


    private View getHeaderView(int position, View v){

        View view = adapter.getHeaderView(position);
        if (view != null){
            v = view;
        }
        v.setFocusable(false);
        return v;
    }

    /**
     * 获得屏幕内的Position
     */
    private void findEyeablePosition(){

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager){
            firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
            lastVisibleItemPosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
        }
    }

    /**
     * 当某个item滚动的时候，屏幕内的其他item跟着滚动
     * @param moveX
     */
    public void scrollTo(int moveX){
        this.offset = moveX;
        findEyeablePosition();

        for(int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            startAnmateView(getAnimateView(i),moveX);
        }
        startAnmateView(findAnimateScrollView(mCurrentHeader),offset);//mCurrentHeader scrollView move
        invalidate();
    }

    private void startAnmateView(AnimateScrollView scrollView,int moveX){
        scrollView.smoothScrollTo(moveX,0);
    }



    /**
     * 滚动的时候刚进入屏幕内的item 增加一个偏移量 offset
     * @param offset
     */
    private void scrollBy(int offset){
        findEyeablePosition();
        for(int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            startScrollBy(getAnimateView(i),offset);
        }
    }

    private void startScrollBy(AnimateScrollView scrollView,int moveX){
        scrollView.scrollTo(moveX,0);
    }


    private AnimateScrollView getAnimateView(int index){
        View childView = findViewHolderForAdapterPosition(index).itemView;
        AnimateScrollView horizontalScrollView = findAnimateScrollView(childView);
        return horizontalScrollView;
    }


    private AnimateScrollView findAnimateScrollView(View v){
        return (AnimateScrollView) v.findViewById(R.id.scrollView);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        this.adapter = (HeaderWrapper) adapter;
        super.setAdapter(adapter);
    }



    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mCurrentHeader == null)
            return;
        int saveCount = canvas.save();
        mCurrentHeader.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

}
