package com.pengxh.app.androidlib;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.TextUtil;
import com.pengxh.app.multilib.widget.swipemenu.SwipeMenu;
import com.pengxh.app.multilib.widget.swipemenu.SwipeMenuCreator;
import com.pengxh.app.multilib.widget.swipemenu.SwipeMenuItem;
import com.pengxh.app.multilib.widget.swipemenu.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import static com.pengxh.app.multilib.utils.DensityUtil.dp2px;

public class MainActivity extends BaseNormalActivity {

    private SwipeMenuListView mSwipeMenuListView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {
        mSwipeMenuListView = findViewById(R.id.mSwipeMenuListView);
    }

    @Override
    public void initEvent() {
        final List<String> testList = getTestList();
        final TestAdapter adapter = new TestAdapter(this, testList);
        mSwipeMenuListView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(255, 0,
                        0)));
                openItem.setWidth(dp2px(getApplicationContext(), 80));
                openItem.setTitle("Delete");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }
        };
        mSwipeMenuListView.setMenuCreator(creator);
        mSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        testList.remove(position);
                        adapter.notifyDataSetChanged();
                        TextUtil.showShortToast(getApplicationContext(), "删除完毕");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private List<String> getTestList() {
        List<String> list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add(i + "号");
        }
        return list;
    }
}
