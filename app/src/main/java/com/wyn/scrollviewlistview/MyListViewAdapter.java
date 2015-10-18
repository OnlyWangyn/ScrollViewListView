package com.wyn.scrollviewlistview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nancy on 15-4-14.
 */
public class MyListViewAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> textList = null;
    private static final int MAX_ITEM_COUNT = 10;
    public MyListViewAdapter(Context context) {

        this.mContext = context;
        this.textList = new ArrayList<>(10);
        String text ="This is item ";
        for(int i=0;i<MAX_ITEM_COUNT;i++){
            StringBuilder sbText = new StringBuilder(text);
            sbText.append(i+1);
            textList.add(sbText.toString());
        }
    }

    @Override
    public int getCount() {

        return textList != null ? textList.size() : 0;
    }

    @Override
    public String getItem(int position) {
        return textList != null ? textList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview,null);
        }

        TextView vtv_folder_name = (TextView)getAdapterView(convertView,R.id.tv_name);
        vtv_folder_name.setText(textList.get(position));
        return convertView;
    }

    private static <T extends View> T getAdapterView(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
