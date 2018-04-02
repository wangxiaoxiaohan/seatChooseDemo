package com.example.lhd.seatchoosedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by LHD on 2018/4/2.
 */

public class sectorView extends FrameLayout {
    private  int  degree;
    private  float radius;
    private  Drawable dragImage;



    public sectorView(Context context) {
        super(context);
    }

    public sectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.sectorView);

            this.degree=array.getInt(R.styleable.sectorView_degree,180);
            this.radius=array.getDimensionPixelSize(R.styleable.sectorView_radius,(int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
            this.dragImage=array.getDrawable(R.styleable.sectorView_dragImage);

        array.recycle();

    }

    public sectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
               int x= (int) event.getRawX();
               int y= (int) event.getRawY();
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN :

                break;
            case  MotionEvent.ACTION_MOVE:

                break;
             case  MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }
}
