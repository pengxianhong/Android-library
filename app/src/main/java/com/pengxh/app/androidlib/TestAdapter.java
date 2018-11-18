package com.pengxh.app.androidlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pengxh.app.multilib.widget.swipemenu.BaseSwipListAdapter;

import java.util.List;

public class TestAdapter extends BaseSwipListAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mDatalist;

    public TestAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.mDatalist = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatalist == null ? 0 : mDatalist.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TestHolder itemHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_test, null);
            itemHolder = new TestHolder();
            itemHolder.mTestTitle = convertView.findViewById(R.id.mTestTitle);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (TestHolder) convertView.getTag();
        }
        itemHolder.bindHolder(mDatalist.get(position));
        return convertView;
    }

    class TestHolder {

        private TextView mTestTitle;

        public void bindHolder(String title) {
            mTestTitle.setText(title);
        }
    }
}
