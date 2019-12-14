package com.pengxh.app.multilib.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pengxh.app.multilib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: 多选功能对话框。ListView和CheckBox焦点会冲突，抉择之下选择放弃CheckBox的焦点
 * @date: 2019/12/13 13:13
 */
public class MultiSelectDialog extends Dialog implements View.OnClickListener {

    private static final String TAG = "MultiSelectDialog";
    private Context context;
    private boolean cancelable;
    private onDialogClickListener listener;
    private String title;
    private List<MultiSelectBean> list;
    private String positiveBtn;
    private String negativeBtn;
    private HashSet<String> hashSet = new HashSet<>();//利用HashSet直接去重

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mutilselect);
        initView();
        setCanceledOnTouchOutside(false);//点击外部区域无法取消对话框
        this.setCancelable(cancelable);
    }

    private void initView() {
        TextView mDialogTitle = findViewById(R.id.mDialogTitle);
        ListView mSelectListView = findViewById(R.id.mSelectListView);
        Button mDialogCancel = findViewById(R.id.mDialogCancel);
        Button mDialogConfirm = findViewById(R.id.mDialogConfirm);

        mDialogCancel.setOnClickListener(this);
        mDialogConfirm.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            mDialogTitle.setText(title);
        }
        if (!TextUtils.isEmpty(negativeBtn)) {
            mDialogCancel.setText(negativeBtn);
        }
        if (!TextUtils.isEmpty(positiveBtn)) {
            mDialogConfirm.setText(positiveBtn);
        }

        MultiSelectAdapter adapter = new MultiSelectAdapter(context, list);
        mSelectListView.setAdapter(adapter);
        mSelectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MultiSelectAdapter.EquipmentListViewHolder viewHolder = (MultiSelectAdapter.EquipmentListViewHolder) view.getTag();
                String des = viewHolder.mTextViewDes.getText().toString();//获取当前item上面的文字
                boolean checked = viewHolder.mCheckBox.isChecked();
                if (checked) {
                    viewHolder.mCheckBox.setChecked(false);
                    hashSet.remove(des);
                } else {
                    viewHolder.mCheckBox.setChecked(true);
                    hashSet.add(des);
                }
            }
        });
    }

    private MultiSelectDialog(Builder builder) {
        super(builder.mContext, R.style.MultiSelectDialog);
        this.context = builder.mContext;
        this.title = builder.title;
        this.list = builder.dataList;
        this.positiveBtn = builder.positiveBtn;
        this.negativeBtn = builder.negativeBtn;
        this.cancelable = builder.cancelable;
        this.listener = builder.listener;
    }


    public static class Builder {
        private Context mContext;
        private String title;//对话框标题
        private List<MultiSelectBean> dataList;//传入的数据
        private String positiveBtn;//确定按钮
        private String negativeBtn;//取消按钮
        private boolean cancelable;//对话框是否可取消
        private onDialogClickListener listener;//按钮监听

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setData(List<MultiSelectBean> list) {
            this.dataList = list;
            return this;
        }

        public Builder setPositiveButton(String name) {
            this.positiveBtn = name;
            return this;
        }

        public Builder setNegativeButton(String name) {
            this.negativeBtn = name;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnDialogClickListener(onDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public MultiSelectDialog build() {
            return new MultiSelectDialog(this);
        }
    }

    public interface onDialogClickListener {
        void onConfirmClick(Dialog dialog, List<String> list);

        void onCancelClick(Dialog dialog);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mDialogCancel) {
            if (listener != null) {
                listener.onCancelClick(this);
            }
        } else if (i == R.id.mDialogConfirm) {
            if (listener != null) {
                listener.onConfirmClick(this, new ArrayList<>(hashSet));
            }
        } else {
            Log.e(TAG, "onClick: Error view ID");
        }
    }

    public class MultiSelectAdapter extends BaseAdapter {

        private Context context;
        private List<MultiSelectBean> beanList;
        private LayoutInflater inflater;
        @SuppressLint("UseSparseArrays")
        private HashMap<Integer, View> viewMap = new HashMap<>();

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
                view = inflater.inflate(R.layout.item_list, null);
                itemHolder = new EquipmentListViewHolder();
                itemHolder.mLeftImage = view.findViewById(R.id.mLeftImage);
                itemHolder.mTextViewTips = view.findViewById(R.id.mTextViewTips);
                itemHolder.mTextViewDes = view.findViewById(R.id.mTextViewDes);
                itemHolder.mCheckBox = view.findViewById(R.id.mCheckBox);
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

            private ImageView mLeftImage;
            private TextView mTextViewTips;
            private TextView mTextViewDes;
            private SmoothCheckBox mCheckBox;

            void bindHolder(MultiSelectBean bean) {
                mTextViewTips.setText(bean.getTips());
                mTextViewDes.setText(bean.getDescription());
                String picture = bean.getPicture();
                if (TextUtils.isEmpty(picture)) {
                    Glide.with(context).load(0).into(mLeftImage);//没有图片就不显示图片
                } else {
                    Glide.with(context).load(picture).into(mLeftImage);
                }
            }
        }
    }
}