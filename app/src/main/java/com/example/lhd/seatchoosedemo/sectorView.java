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
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by LHD on 2018/4/2.
 */

public class sectorView extends View {
    private  float  degree;
    private  Drawable mirrorImage;
    int mLastX;
    int mLastY;
    private Paint mdragCirclePaint;
    private  Paint mInPaint;
    private  Paint mOutPaint;
    private  Paint mLinePaint;
    private  Paint mBitmapPaint;
    private Bitmap mirrorBitmap;
    private  float LineAngle;

    public sectorView(Context context) {
        super(context);
    }

    public sectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.sectorView);

   //         this.degree=array.getInt(R.styleable.sectorView_degree,180);
   //            this.InRadius=array.getDimensionPixelSize(R.styleable.sectorView_radius,(int) TypedValue.applyDimension(
   //                    TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
            this.mirrorImage=array.getDrawable(R.styleable.sectorView_mirrorImage);

        array.recycle();

        mirrorBitmap=DrawableToBitmap(mirrorImage);

        //小球
        mdragCirclePaint=new Paint();
        mdragCirclePaint.setAntiAlias(true);
        mdragCirclePaint.setColor(getResources().getColor(R.color.blue));

         //范围绿色
        mInPaint=new Paint();
        mInPaint.setAntiAlias(true);
        mInPaint.setColor(getResources().getColor(R.color.transparentGreen));

        // 范围蓝色
        mOutPaint=new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(getResources().getColor(R.color.transparentBlue));

        //球和后视镜的连接线
         mLinePaint=new Paint();
         mLinePaint.setAntiAlias(true);
         mLinePaint.setColor(getResources().getColor(R.color.transparentWhite));
         mLinePaint.setStrokeWidth(3);
         //画后视镜图案
         mBitmapPaint=new Paint();
         mBitmapPaint.setAntiAlias(true);
         mBitmapPaint.setFilterBitmap(true);
         mBitmapPaint.setDither(true);

    }

    public sectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //获取控件宽高
        int x=getWidth()/2;
        int y=getHeight()/2;
         
         int drawableHeight=mirrorBitmap.getHeight();
        //设置扇形的轮廓矩形
         float retcfBorder= (float) Math.sqrt(mLastX*mLastX+(y-mLastY)*(y-mLastY));
         RectF rectF=new RectF(-retcfBorder,y-retcfBorder,retcfBorder,y+retcfBorder);
         float inRetcfBorder=retcfBorder*2/3;
         RectF inRectF =new RectF(-inRetcfBorder,y-inRetcfBorder,inRetcfBorder,y+inRetcfBorder);
       // 中间线的角度
         float MidAnglePi= (float) Math.atan((float)(mLastY-y)/mLastX);
         float MidAngle=(float) ((MidAnglePi*180)/Math.PI);
         LineAngle=MidAngle;

      // 扫过的角度

         float sweepAngle= degree;
       // 画圆，画扇形,画连接线
         canvas.drawArc(rectF,MidAngle-sweepAngle/2,sweepAngle,true,mOutPaint);
         canvas.drawArc(inRectF,MidAngle-sweepAngle/2,sweepAngle,true,mInPaint);
         canvas.drawLine(0,y,mLastX,mLastY,mLinePaint);
         canvas.drawCircle(mLastX,mLastY,11,mdragCirclePaint);
         canvas.drawBitmap(mirrorBitmap,0,y-drawableHeight/2,mBitmapPaint);
         super.onDraw(canvas);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLastX=w/2;
        mLastY=h/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
               int x= (int) event.getX();
               int y= (int) event.getY();
               switch (event.getAction()){
                case  MotionEvent.ACTION_DOWN :
                    if (Math.abs(x-mLastX)<35 || Math.abs(y-mLastY)<35){

                        return  true;
                    }
                    break;
                case  MotionEvent.ACTION_MOVE:

                    mLastX=x;
                    mLastY=y;
                    invalidate();
                    Log.d("看我看我", "onDraw: "+ LineAngle);
                    break;
                case  MotionEvent.ACTION_UP:

                    break;

           }
          return  true;
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
   public  void  setDegree(float f){
        this.degree=f;
        invalidate();

     }
     public int getUserAngle(){
       return (int) degree;
     }
     public float getLineAngle(){
         return  LineAngle;

     }

}
