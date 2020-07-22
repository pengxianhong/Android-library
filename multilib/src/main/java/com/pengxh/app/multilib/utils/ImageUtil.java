package com.pengxh.app.multilib.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.pengxh.app.multilib.interfaces.BitmapCallBackListener;
import com.pengxh.app.multilib.interfaces.ImageCompressListener;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ImageUtil {

    private static final String TAG = "ImageUtil";

    /**
     * 根据URL返回Bitmap对象
     */
    public static void obtainBitmap(final String url, final BitmapCallBackListener listener) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(final ObservableEmitter<Bitmap> emitter) throws Exception {
                Bitmap bitmap = null;
                try {
                    URL imageUrl = new URL(url);
                    // 获得连接
                    HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                    conn.setConnectTimeout(10 * 1000);//设置超时
                    conn.setDoInput(true);
                    conn.setUseCaches(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                emitter.onNext(bitmap);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                if (bitmap != null) {
                    listener.onSuccess(bitmap);
                } else {
                    listener.onFailure(new NullPointerException());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * @param context       上下文
     * @param imagePathList 原图Uri集合
     * @param listener      监听回调
     */
    public static void compressBatchImage(Context context, final List<String> imagePathList, final ImageCompressListener listener) {
        File compressDir = new File(Environment.getExternalStorageDirectory(), "ImageCompress");
        if (!compressDir.exists()) {
            compressDir.mkdir();
        }
        final List<String> newImagePath = new ArrayList<>();
        Luban.with(context).load(imagePathList).ignoreBy(100).setTargetDir(compressDir.getPath()).filter(new CompressionPredicate() {
            @Override
            public boolean apply(String path) {
                return !(TextUtils.isEmpty(path) || path.toLowerCase(Locale.ROOT).endsWith(".gif"));
            }
        }).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                if (file == null) {
                    return;
                }
                newImagePath.add(file.getAbsolutePath());
                if (newImagePath.size() == imagePathList.size()) {
                    listener.onSuccess(newImagePath);
                }
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }
        }).launch();
    }

    /**
     * 只能获取content类型的FileProvide
     */
    public static String getImagePath(Context context, Uri contentUri) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                try {
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                } catch (CursorIndexOutOfBoundsException ex) {
                    Log.w(TAG, ex.getCause());
                }
            }
            cursor.close();
        }
        return path;
    }
}