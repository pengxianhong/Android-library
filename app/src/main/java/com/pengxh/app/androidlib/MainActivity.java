package com.pengxh.app.androidlib;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pengxh.app.multilib.base.BaseNormalActivity;
import com.pengxh.app.multilib.utils.ImageCompressListener;
import com.pengxh.app.multilib.utils.ImageUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseNormalActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_SELECT = 100;

    @BindView(R.id.mStartTest)
    Button mStartTest;
    @BindView(R.id.mStartCompress)
    Button mStartCompress;
    @BindView(R.id.testIV)
    ImageView testIV;
    private List<String> pathList=new ArrayList<>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.mStartTest, R.id.mStartCompress})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mStartTest:
                Matisse.from(this)
                        .choose(MimeType.ofImage())
                        .countable(true) //是否显示数字
                        .showSingleMediaType(true)
                        //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                        .capture(true)
                        //参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                        .captureStrategy(new CaptureStrategy(true, "com.pengxh.app.androidlib.fileprovider"))
                        .countable(true)
                        //最大选择数量为9
                        .maxSelectable(9)
                        //选择方向
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        //界面中缩略图的质量
                        .thumbnailScale(0.85f)
                        //蓝色主题
                        .theme(R.style.Matisse_Zhihu)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_SELECT);
                break;
            case R.id.mStartCompress:
                ImageUtil.compressBatchImage(this, pathList, new ImageCompressListener() {
                    @Override
                    public void onSuccess(List<String> result) {
                        Log.d(TAG, "onSuccess: " + result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d(TAG, "onFailure: "+t);
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            //Uri需要转换为Path
            for (Uri uri : uris) {
                pathList.add(ImageUtil.getImagePath(this,uri));
            }

            Glide.with(this).load(uris.get(0)).into(testIV);
        }
    }
}