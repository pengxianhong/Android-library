package com.pengxh.app.multilib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pengxh.app.multilib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @description: TODO
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @date: 2019/12/15 15:39
 */
public class MultiSelectAdapter extends BaseAdapter {
    private Context context;
    private List<MultiSelectBean> beanList;
    private LayoutInflater inflater;
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, View> viewMap = new HashMap<>();
    private HashSet<String> hashSet = new HashSet<>();//利用HashSet直接去重

    MultiSelectAdapter(Context context, List<MultiSelectBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        EquipmentListViewHolder itemHolder;
        if (viewMap.get(position) == null) {
            view = inflater.inflate(R.layout.item_multiselect_list, null);
            itemHolder = new EquipmentListViewHolder();
            itemHolder.mStartImage = view.findViewById(R.id.mStartImage);
            itemHolder.mTextViewTips = view.findViewById(R.id.mTextViewTips);
            itemHolder.mTextViewDes = view.findViewById(R.id.mTextViewDes);
            itemHolder.mCheckBox = view.findViewById(R.id.mSmoothCheckBox);
            viewMap.put(position, view);
            view.setTag(itemHolder);
        } else {
            view = viewMap.get(position);
            itemHolder = (EquipmentListViewHolder) view.getTag();
        }
        itemHolder.bindHolder(beanList.get(position));
        return view;
    }

    class EquipmentListViewHolder {

        ImageView mStartImage;
        TextView mTextViewTips;
        TextView mTextViewDes;
        SmoothCheckBox mCheckBox;

        void bindHolder(MultiSelectBean bean) {
            mTextViewTips.setText(bean.getTips());
            final String description = bean.getDescription();
            mTextViewDes.setText(description);
            String picture = bean.getPicture();
            if (TextUtils.isEmpty(picture)) {
                Glide.with(context).load(0).into(mStartImage);//没有图片就不显示图片
            } else {
                Glide.with(context).load(picture).into(mStartImage);
            }
            mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checked = mCheckBox.isChecked();
                    if (checked) {
                        mCheckBox.setChecked(false);
                        hashSet.remove(description);
                    } else {
                        mCheckBox.setChecked(true);
                        hashSet.add(description);
                    }
                    checkedListener.getDataList(new ArrayList<>(hashSet));
                }
            });
        }
    }

    private OnCheckedListener checkedListener;

    public interface OnCheckedListener {
        void getDataList(List<String> list);
    }

    void setOnCheckedListener(OnCheckedListener listener) {
        this.checkedListener = listener;
    }
}
