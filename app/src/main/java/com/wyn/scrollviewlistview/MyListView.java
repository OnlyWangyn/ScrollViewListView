package com.wyn.scrollviewlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by nancy on 15-10-18.
 */
public class MyListView extends ListView implements View.OnTouchListener, AbsListView.OnScrollListener {
    private static final int MAX_LIST_ITEM_COUNT = 100;
    private int listViewTouchAction;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        listViewTouchAction = -1;
        setOnScrollListener(this);
        setOnTouchListener(this);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newHeight = 0;
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heigtSize = MeasureSpec.getSize(heightMeasureSpec);

        if(heightMode != MeasureSpec.EXACTLY){
           ListAdapter adapter = getAdapter();
           if(adapter != null && !adapter.isEmpty()){
               int position =0;
               while(position < adapter.getCount() && position < MAX_LIST_ITEM_COUNT){
                  View listItem = adapter.getView(position, null,
                          this);
                  if(listItem instanceof ViewGroup){
                      listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                              ViewGroup.LayoutParams.WRAP_CONTENT));
                  }
                  listItem.measure(widthMeasureSpec,heightMeasureSpec);
                  newHeight += listItem.getMeasuredHeight();
                  position++;
              }
               newHeight += getDividerHeight() * position;
               if(heightMode == MeasureSpec.AT_MOST){
                   newHeight = Math.min(newHeight,heigtSize);
               }
           }
        }else{
            newHeight = getMeasuredHeight();
        }
        setMeasuredDimension(getMeasuredWidth(), newHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getAdapter() != null && getAdapter().getCount() > MAX_LIST_ITEM_COUNT) {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                scrollBy(0, 1);
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (getAdapter() != null && getAdapter().getCount() > MAX_LIST_ITEM_COUNT) {
            if (listViewTouchAction == MotionEvent.ACTION_MOVE) {
                scrollBy(0, -1);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getAdapter() != null && getAdapter().getCount() > MAX_LIST_ITEM_COUNT) {
            if (listViewTouchAction == MotionEvent.ACTION_MOVE) {
                scrollBy(0, 1);
            }
        }
        return false;
    }
}
