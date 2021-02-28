package com.example.customviewhomework.data;

import android.graphics.Paint;
import android.graphics.Rect;

public class CurrentData {

    private final Paint textPaint = new Paint();
    private final Paint rectPaint = new Paint();
    private final Rect rect = new Rect();
    private String stringValue;

    public CurrentData(String val){
        stringValue = val;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(Paint textPaint) {
        this.textPaint.set(textPaint);
    }

    public Paint getRectPaint() {
        return rectPaint;
    }

    public void setRectPaint(Paint rectPaint) {

        this.rectPaint.set(rectPaint);
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(int left, int top, int right, int bottom) {

        this.rect.set(left, top, right, bottom);
    }
}

