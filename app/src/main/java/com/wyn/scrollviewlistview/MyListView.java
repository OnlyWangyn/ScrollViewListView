package com.wyn.scrollviewlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by nancy on 15-10-18.
 */
public class MyListView extends ListView {
    private static final int MAX_LIST_ITEM_COUNT = 100;
    private int listViewTouchAction;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        listViewTouchAction = -1;
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
        //calculate
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
}
