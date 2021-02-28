package com.example.customviewhomework.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.example.customviewhomework.R;
import com.example.customviewhomework.data.CurrentData;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyCustomView extends View {

    private float previous_x;
    private float init_val;
    private String name;
    private float dx;
    private int textHeight = 96;
    ArrayList<CurrentData> data = new ArrayList<>();

    private int state = 1;

    public MyCustomView(Context context) {
        super(context, null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustomView, 0, 0);
            try {
                name = a.getString(R.styleable.MyCustomView_text);
            } finally {
                a.recycle();
            }
        }
        data.add(new CurrentData("Block"));
        data.add(new CurrentData(name));
        data.add(new CurrentData("Call"));

        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(96);
        mPaint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < data.size(); ++i) {
            data.get(i).setTextPaint(mPaint);
        }

        mPaint.setColor(Color.RED);
        data.get(0).setRectPaint(mPaint);

        mPaint.setColor(Color.WHITE);
        data.get(1).setRectPaint(mPaint);

        mPaint.setColor(Color.GREEN);
        data.get(2).setRectPaint(mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 2000;
        int desiredHeight = 120;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float center = getWidth() / 2.0f;
        data.get(state).setRect(0, 0, getWidth(), 120);

        if (dx == 0) {
            canvas.drawRect(data.get(state).getRect(), data.get(state).getRectPaint());
            canvas.drawText(data.get(state).getStringValue(), center, textHeight, data.get(state).getTextPaint());
        }
        if (dx != 0) {
            data.get(state).setRect((int) (0 + dx), 0, (int) (getWidth() + dx), 120);
            canvas.drawRect(data.get(state).getRect(), data.get(state).getRectPaint());
            canvas.drawText(data.get(state).getStringValue(), center + dx, textHeight, data.get(state).getTextPaint());
            if (dx < 0) {
                if (state == 1 || state == 0) {
                    data.get(state + 1).setRect(getWidth() + (int) dx, 0, getWidth() + getWidth() + (int) dx, 120);
                    canvas.drawRect(data.get(state + 1).getRect(), data.get(state + 1).getRectPaint());
                    canvas.drawText(data.get(state + 1).getStringValue(), getWidth() + getWidth() / 2.0f + dx, textHeight, data.get(state + 1).getTextPaint());
                }
            } else {
                if (state == 1 || state == 2) {
                    data.get(state - 1).setRect(-getWidth() + (int) dx, 0, (int) dx, 120);
                    canvas.drawRect(data.get(state - 1).getRect(), data.get(state - 1).getRectPaint());
                    canvas.drawText(data.get(state - 1).getStringValue(), -getWidth() / 2.0f + dx, textHeight, data.get(state - 1).getTextPaint());
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                init_val = event.getX();
                previous_x = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                if (previous_x < x) {
                    dx += x - previous_x;
                } else {
                    dx -= previous_x - x;
                }
                invalidate();
                previous_x = x;
                break;

            case MotionEvent.ACTION_UP:
                float diff = init_val - event.getX();
                if (Math.abs(diff) > getWidth() / 3.0f) {
                    if (diff > 0) {
                        if (state == 1) {
                            state = 2;
                        } else if (state == 0) {
                            state = 1;
                        }
                        animateView(event.getX(), 0, 500);
                    } else {
                        if (state == 1) {
                            state = 0;
                        } else if (state == 2) {
                            state = 1;
                        }
                    }
                } else {
                    animateView(dx, 0, 500);
                }
                dx = 0;
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                animateView(dx, 0, 250);
                dx = 0;
                invalidate();
        }
        return true;
    }

    public void animateView(float start, float end, int duration) {
        ValueAnimator objectAnimator = ValueAnimator.ofFloat(start, end);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.addUpdateListener(animation -> {
            dx = (float) animation.getAnimatedValue();
            invalidate();
        });
        objectAnimator.start();
    }

    public void setText(String name) {
        data.get(1).setStringValue(name);
        invalidate();
    }

}
