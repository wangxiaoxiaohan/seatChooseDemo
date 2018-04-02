package com.example.lhd.seatchoosedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
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

public class sectorView extends View {
    private  int  degree;
    private  float InRadius;
    private  float OutRadius;
    private  Drawable mirrorImage;
    int mLastX=400;
    int mLastY=400;
    private Paint mdragCirclePaint;
    private  Paint mInPaint;
    private  Paint mOutPaint;
    private Bitmap mirrorBitmap;





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
        mdragCirclePaint=new Paint();
        mdragCirclePaint.setAntiAlias(true);
        mdragCirclePaint.setColor(Color.parseColor("#00ff99"));

        mInPaint=new Paint();
        mInPaint.setAntiAlias(true);
        mInPaint.setColor(Color.parseColor("#00ff18"));


        mOutPaint=new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(Color.parseColor("#00fffc"));




    }

    public sectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int x=getWidth()/2;
        int y=getHeight()/2;
         float retcfBorder= (float) Math.sqrt(mLastX*mLastX+mLastY*mLastY);
         RectF rectF=new RectF(-retcfBorder,y-retcfBorder,retcfBorder,y+retcfBorder);

         canvas.drawCircle(mLastX,mLastY,50,mdragCirclePaint);
//         float sweepAnglePi= (float) Math.atan((y-mLastY)/mLastX);
//         float sweepAngle= (float) (sweepAnglePi*180/Math.PI);

       //  canvas.drawArc(rectF,0-sweepAngle/2,sweepAngle,true,mOutPaint);
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
                if (Math.sqrt(x-mLastX)>50 && Math.sqrt(y-mLastY)>50)
                    return  true;
                break;
            case  MotionEvent.ACTION_MOVE:



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

}
