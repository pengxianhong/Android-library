package com.pengxh.app.multilib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.pengxh.app.multilib.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 验证码控件
 */
public class VerificationCodeView extends View {

    private static final String TAG = "VerificationCodeView";

    /**
     * 验证码文字
     */
    private String mText;

    /**
     * 验证码文字长度
     */
    private int mTextLenth;

    /**
     * 验证码默认文字长度
     */
    private int mDefTextLenth = 4;

    /**
     * 验证码文字大小
     */
    private int mTextSize;

    /**
     * 验证码颜色
     */
    private int mTextColor;

    /**
     * 验证码View背景色
     */
    private int mViewBgColor;

    /**
     * 画笔属性
     */
    private Rect mRect;
    private Paint mPaint;

    public VerificationCodeView(Context context) {
        this(context, null);
    }

    public VerificationCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerificationCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取到attrs属性
         * */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerificationCodeView, defStyleAttr, 0);
        int index = typedArray.getIndexCount();//attr属性条目
        for (int i = 0; i < index; i++) {
            int arrayIndex = typedArray.getIndex(i);
            if (arrayIndex == R.styleable.VerificationCodeView_text) {
                if (TextUtils.isEmpty(typedArray.getString(arrayIndex))) {
                    Log.e(TAG, "mText is null,please check xml");
                } else {
                    mText = typedArray.getString(arrayIndex);
                }
            } else if (arrayIndex == R.styleable.VerificationCodeView_text_size) {
                mTextSize = typedArray.getDimensionPixelSize(arrayIndex, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (arrayIndex == R.styleable.VerificationCodeView_text_color) {
                mTextColor = typedArray.getColor(arrayIndex, Color.BLACK);
            } else if (arrayIndex == R.styleable.VerificationCodeView_view_background) {
                mViewBgColor = typedArray.getColor(arrayIndex, Color.YELLOW);
            } else if (arrayIndex == R.styleable.VerificationCodeView_text_length) {
                mTextLenth = typedArray.getInt(arrayIndex, mDefTextLenth);
            }
        }
        typedArray.recycle();
        /**
         * 初始化画笔
         * */
        initPaint();
        initEvent();
    }

    private void initEvent() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击获取随机数
                 * */
                mText = randomNumber(mTextLenth);
                postInvalidate();
            }
        });
    }

    /**
     * 随机数
     */
    private String randomNumber(int length) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            //可根据需求修改随机数个数
            int nextInt = random.nextInt(10);
            list.add(nextInt);
        }
        StringBuilder number = new StringBuilder();
        for (Integer index : list) {
            number.append("").append(index);
        }
        return number.toString();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mRect = new Rect();
        if (TextUtils.isEmpty(mText)) {
            Log.e(TAG, "mText is null,please check xml");
        } else {
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
        }
    }

    /**
     * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     * UNSPECIFIED：表示子布局想要多大就多大，很少使用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            if (TextUtils.isEmpty(mText)) {
                Log.e(TAG, "mText is null,please check xml");
            } else {
                mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            }
            float textWidth = mRect.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            if (TextUtils.isEmpty(mText)) {
                Log.e(TAG, "mText is null,please check xml");
            } else {
                mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            }
            float textHeight = mRect.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 获取到自定义的view宽高
         * */
        final int height = getHeight();
        final int width = getWidth();

        /**
         * 背景色
         * */
        mPaint.setColor(mViewBgColor);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        /**
         * 文字
         * */
        mPaint.setColor(mTextColor);
        if (TextUtils.isEmpty(mText)) {
            Log.e(TAG, "mText is null,please check xml");
        } else {
            canvas.drawText(mText, (getWidth() - mRect.width()) / 2, (getHeight() + mRect.height()) / 2, mPaint);
        }
        /**
         * 噪点---点
         * */
        int[] point;
        for (int i = 0; i < 100; i++) {
            point = getPoint(height, width);
            mPaint.setColor(randomColor());
            canvas.drawCircle(point[0], point[1], 3, mPaint);
        }

        /**
         * 噪点---线
         * */
        int[] line;
        for (int i = 0; i < 20; i++) {
            line = getLine(height, width);
            mPaint.setColor(randomColor());
            mPaint.setStrokeWidth(3);
            canvas.drawLine(line[0], line[1], line[2], line[3], mPaint);
        }
    }

    /**
     * 随机颜色
     */
    private int randomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

    private int[] getPoint(int height, int width) {
        int[] tempCheckNum = {0, 0, 0, 0};
        tempCheckNum[0] = (int) (Math.random() * width);
        tempCheckNum[1] = (int) (Math.random() * height);
        return tempCheckNum;
    }

    private int[] getLine(int height, int width) {
        int[] tempCheckNum = {0, 0, 0, 0};
        for (int i = 0; i < 4; i += 2) {
            tempCheckNum[i] = (int) (Math.random() * width);
            tempCheckNum[i + 1] = (int) (Math.random() * height);
        }
        return tempCheckNum;
    }
}