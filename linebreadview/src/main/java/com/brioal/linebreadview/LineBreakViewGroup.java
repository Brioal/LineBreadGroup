package com.brioal.linebreadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Github:https://github.com/Brioal
 * Emalil : brioal@foxmail.com
 * Created by brioal on 17-3-15.
 */

public class LineBreakViewGroup extends ViewGroup {
    private int mWidth;
    private int mHeight;

    public LineBreakViewGroup(Context context) {
        this(context, null);
    }

    public LineBreakViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int w = getPaddingLeft() + getPaddingRight();
        mHeight = getPaddingTop() + getPaddingBottom();
        for (int i = 0; i < getChildCount(); i++) {
            View tv = getChildAt(i);
            measureChild(tv, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) tv.getLayoutParams();
            if (mHeight == getPaddingTop() + getPaddingBottom()) {
                mHeight += tv.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            }
            if (w + tv.getMeasuredWidth() + params.leftMargin + params.rightMargin > mWidth) {
                mHeight += tv.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                w = getPaddingLeft() + getPaddingRight();
            }
            w += tv.getMeasuredWidth() + params.leftMargin + params.rightMargin;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startX = getPaddingLeft();
        int startY = getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            View tv = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) tv.getLayoutParams();
            if (startX + tv.getMeasuredWidth() + params.leftMargin + params.rightMargin > mWidth) {
                startX = getPaddingLeft();
                startY += tv.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            }
            tv.layout(startX + params.leftMargin, startY + params.topMargin, startX + params.leftMargin + tv.getMeasuredWidth(), startY + params.topMargin + tv.getMeasuredHeight());
            startX += params.leftMargin + tv.getMeasuredWidth() + params.rightMargin;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
