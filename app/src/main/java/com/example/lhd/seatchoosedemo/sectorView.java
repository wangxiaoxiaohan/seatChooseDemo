package com.example.lhd.seatchoosedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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
    private  float InRadius;
    private  float OutRadius;
    private  Drawable mirrorImage;
    int mLastX;
    int mLastY;
    private Paint mPaint;
    private Bitmap  mirrorBitmap;
    private Matrix matrix;
    private  ImageView dragImageView;



    public sectorView(Context context) {
        super(context);
    }

    public sectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.sectorView);

            this.degree=array.getInt(R.styleable.sectorView_degree,180);
            this.InRadius=array.getDimensionPixelSize(R.styleable.sectorView_radius,(int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
            this.mirrorImage=array.getDrawable(R.styleable.sectorView_mirrorImage);
        array.recycle();

        mirrorBitmap=DrawableToBitmap(mirrorImage);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        matrix=new Matrix();


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
                  int deltaX=x-mLastX;
                  int deltaY=y-mLastY;
                break;
             case  MotionEvent.ACTION_UP:

                break;

        }
        mLastX=x;
        mLastY=y;
        return true;
    }
    private Bitmap DrawableToBitmap(Drawable drawable){
        if (drawable==null){
            return null;}
        int w=drawable.getIntrinsicWidth();
        int h=drawable.getIntrinsicHeight();
        Bitmap bitmap=Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
        Canvas canvas =new Canvas(bitmap);//准备bitmap画布
        drawable.setBounds(0,0,w,h);
        drawable.draw(canvas);//将drawable 画bitmap画布上。
        return bitmap;
    }
    public   void  setDragImageView(ImageView imageView){
        this.dragImageView=imageView;
        invalidate();
    }
}
